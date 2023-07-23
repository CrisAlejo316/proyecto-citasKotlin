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
        val pat = patientRepository.save(
                Patient(
                        nombre = request.nombre,
                        apellido = request.apellido,
                        identificacion = request.identificacion,
                        telefono = request.telefono,
                )
        )
        return PatientResponse(
                idPaciente = 1,
                nombre = request.nombre,
                apellido = request.apellido,
                identificacion = request.identificacion,
                telefono = request.telefono,
                created_at = Instant.now(),
        )
    }

    fun getAllPatients(): List<Patient>{
        var response = patientRepository.findAll()
        return response
    }

    fun getPatientById(id:Long): List<Patient>{
        var patient = patientRepository.getPatientById(id)
        return patient
    }

    fun deletePatientById(id:Long):Unit{
        var delete = patientRepository.deletePatientById(id)
    }

    fun updatePatient(id: Long, request: PatientRequest): String {
        try {
            patientRepository.updatePatientById(id, request.nombre, request.apellido, request.identificacion, request.telefono)
            return "La actualización fue exitosa"
        } catch (ex: EmptyResultDataAccessException) {
            return "Error: No se encontró un doctor con el ID especificado"
        } catch (ex: DataAccessException) {
            return "La actualización fue exitosa"
        }
    }

}