package com.citas.citas.controllers

import com.citas.citas.constants.*
import com.citas.citas.domains.entity.Patient
import com.citas.citas.domains.request.CreateDoctorRequest
import com.citas.citas.domains.request.PatientRequest
import com.citas.citas.domains.responses.HealthCheckResponse
import com.citas.citas.domains.responses.PatientResponse
import com.citas.citas.services.PatientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
class PatientController {
    @Autowired
    private lateinit var patientService: PatientService

    @GetMapping(Patient)
    fun getAllPatients(): List<Patient> = patientService.getAllPatients()

    @PostMapping(CreatePatients)
    fun createPatient(@RequestBody @Validated request: PatientRequest): ResponseEntity<PatientResponse> {
        return try{
            ResponseEntity(patientService.createPatient(request), HttpStatus.CREATED)
        }catch(e: Error){
            ResponseEntity(PatientResponse(message=e.message), HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping(GetPatientById)
    fun getPatientById(@PathVariable("id") id: Long): PatientResponse {
        return try{
            PatientResponse("Patient $id found",patientService.getPatientById(id))
        }catch(e: Error){
            PatientResponse(e.message)
        }
    }

    @PutMapping(UpdatePatient)
    fun updateDoctor(
        @PathVariable("id") id: Long,
        @RequestBody @Validated request: UpdatePatientRequest
    ): ResponseEntity<PatientResponse>{
        return try{
            ResponseEntity(patientService.updatePatient(id, request), HttpStatus.ACCEPTED)
        }catch(e: Error){
            ResponseEntity(PatientResponse(message=e.message), HttpStatus.BAD_REQUEST)
        }
    }
	
    @DeleteMapping(DeletePatient)
    fun deletePatient(@PathVariable("id")id:Long): ResponseEntity<PatientResponse>{
        return try{
            ResponseEntity(patientService.deletePatientById(id), HttpStatus.ACCEPTED)
        }catch(e: Error){
            ResponseEntity(PatientResponse(message=e.message), HttpStatus.BAD_REQUEST)
        }
    }



