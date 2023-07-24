package com.citas.citas.services

import com.citas.citas.domains.entity.Patient
import com.citas.citas.domains.request.CreateDoctorRequest
import com.citas.citas.domains.request.PatientRequest
import com.citas.citas.domains.responses.PatientResponse
import com.citas.citas.repositories.PatientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataAccessException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class PatientService {
    @Autowired
    private lateinit var patientRepository: PatientRepository

    fun createPatient(request: PatientRequest): PatientResponse{
        try{
            print(request)
            val patient = patientRepository.save(
                Patient(
                    nombre = request.nombre,
                    apellido = request.apellido,
                    identificacion = request.identificacion,
                    telefono = request.telefono,
                )
            )
            return PatientResponse(
                message = "Patient created successfully",
                patient = patient
            )
        }catch(e: Error){
            throw Error(e.message)
        }
    }

    fun getAllPatients(): List<Patient> = patientRepository.findAll()

    fun getPatientById(id:Long): Patient{
        val patient = patientRepository.findByIdOrNull(id)
        if (patient !== null){
            return patient
        }
        throw Error("Patient not found")
    }

	fun getPatientByIdentificacion(identificacion: String): Patient{
        val patient = patientRepository.getPatientByIdentificacion(identificacion)
        if (patient !== null){
            return patient
        }
        throw Error("Patient not found")
    }
	
	
    fun deletePatientById(id:Long): PatientResponse {
        return try{
            val patient = getPatientById(id)
            patientRepository.deleteById(id)
            PatientResponse("El registro se ha borrado con exito", patient)
        } catch (e: Error) {
            PatientResponse(e.message)
        }
    }

    fun updatePatient(id: Long, request: UpdatePatientRequest): PatientResponse {
        try {
            val patient = getPatientById(id)
            val newPatient = Patient(
                idpaciente = patient.idpaciente,
                nombre = if (request.nombre!==null) request.nombre else patient.nombre,
                apellido = if (request.apellido!==null) request.apellido else patient.apellido,
                identificacion = if (request.identificacion!==null) request.identificacion else patient.identificacion,
                telefono = if (request.telefono!==null) request.telefono else patient.telefono,
            )
            val updatePatient = patientRepository.save(newPatient)
            return PatientResponse("Patient update", updatePatient)
        } catch (e: Error) {
            throw Error(e.message)
        }
    }

}