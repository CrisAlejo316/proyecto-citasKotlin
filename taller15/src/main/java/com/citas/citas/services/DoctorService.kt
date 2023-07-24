package com.citas.citas.services

import ch.qos.logback.core.net.SyslogOutputStream
import com.citas.citas.domains.request.CreateDoctorRequest
import com.citas.citas.domains.responses.CreateDoctorResponse
import com.citas.citas.repositories.DoctorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.Instant
import com.citas.citas.domains.entity.Doctor
import org.springframework.dao.DataAccessException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException


@Service
class DoctorService {
    @Autowired
    private lateinit var doctorRepository: DoctorRepository

    
    fun createDoctor(request: CreateDoctorRequest): DoctorResponse {
        try{
            validEspecialidad(request.especialidad)
            val doctor = doctorRepository.save(
                Doctor(
                    nombre = request.nombre,
                    apellido = request.apellido,
                    especialidad = request.especialidad,
                    correo = request.correo,
                    consultorio = request.consultorio,
                )
            )
            return DoctorResponse(
                message = "Doctor created successfully",
                doctor = doctor
            )
        }catch (e: Error){
            throw Error(e.message)
        }
    }

    fun getAllDoctors(): List<Doctor> = doctorRepository.findAll()

    fun getDoctorById(id: Long): Doctor {
        val doctor = doctorRepository.findByIdOrNull(id)
        if (doctor !== null){
            return doctor
        }
        throw Error("Doctor not found")
    }
	
    fun updateDoctor(id: Long, request: UpdateDoctorRequest): DoctorResponse {
        try {
            val doctor = getDoctorById(id)
            val newDoctor = Doctor(
                idDoctor = doctor.idDoctor,
                nombre = if (request.nombre!==null) request.nombre else doctor.nombre,
                apellido = if (request.apellido!==null) request.apellido else doctor.apellido,
                especialidad = if (request.especialidad!==null) request.especialidad else doctor.especialidad,
                consultorio = if (request.consultorio!==null) request.consultorio else doctor.consultorio,
                correo = if (request.correo!==null) request.correo else doctor.correo,
            )
            validEspecialidad(newDoctor.especialidad)
            val updateDoctor = doctorRepository.save(newDoctor)
            return DoctorResponse("Doctor update", updateDoctor)
        } catch (e: Error){
            throw Error(e.message)
        }
    }

    fun deleteDoctor(id: Long): DoctorResponse {
        return try {
            val doctor = getDoctorById(id)
            doctorRepository.deleteById(id)
            DoctorResponse("El registro se ha borrado con exito", doctor)
        }catch(e: DataIntegrityViolationException){
            DoctorResponse("No se puede borrar el doctor. Tiene una cita asignada.")
        } catch (e: Error) {
            DoctorResponse(e.message)
        }
    }
	
	fun validEspecialidad(especialidad: String){
        Especialidades.values().find { it.especialidad == especialidad }
            ?: throw Error("Especialidad not found")
    }
	
}
