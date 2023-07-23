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

    // Dejemoslo Quieto
    fun createDoctor(request: CreateDoctorRequest): CreateDoctorResponse {
        val doctor = doctorRepository.save(
                Doctor(
                        nombre = request.nombre,
                        apellido = request.apellido,
                        especialidad = request.especialidad,
                        correo = request.correo,
                        consultorio = request.consultorio,
                )
        )
        return CreateDoctorResponse(
                idDoctor = doctor.idDoctor,
                nombre = request.nombre,
                apellido = request.apellido,
                especialidad = request.especialidad,
                correo = request.correo,
                consultorio = request.consultorio,
                createAt = Instant.now()
        )
    }

    // Dejemoslo quieto
    fun getAllDoctors(): List<Doctor> {
        var response = doctorRepository.findAll()
        return response
    }

    // Dejemoslo Quieto
    fun getDoctorById(id: Long): List<Doctor> {
        var doctor = doctorRepository.getByDoctorId(id)
        return doctor
    }

    fun updateDoctor(id: Long, request: CreateDoctorRequest): String {
        try {
            doctorRepository.updateDoctorById(id, request.especialidad)
            return "La actualización fue exitosa"
        } catch (ex: EmptyResultDataAccessException) {
            // Si el doctor con el ID dado no existe en la base de datos
            return "Error: No se encontró un doctor con el ID especificado"
        } catch (ex: DataAccessException) {
            return "La actualización fue exitosa"
        }
    }

    fun deleteDoctor(id: Long): String {
        try{
            doctorRepository.deleteDoctorByIdDoctor(id)
            return "El registro se ha borrado con exito"
        } catch (ex: DataAccessException) {
            return "El registro se ha borrado con exito"
        }
    }
}
