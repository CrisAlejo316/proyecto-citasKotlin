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
    fun createPatient(
            @RequestBody @Validated request: PatientRequest
    ): PatientResponse = patientService.createPatient(request)

    @GetMapping(GetPatientById)
    fun getPatienttById(
            @PathVariable("id") id: Long
    ): List<Patient> = patientService.getPatientById(id)

    @PutMapping(UpdateDoctor)
    fun updateDoctor(
        @PathVariable("id") id: Long,
        @RequestBody @Validated request: PatientRequest
    ): String  = patientService.updatePatient(id, request)

    @DeleteMapping(DeletePatient)
    fun deletePatient(
            @PathVariable("id")id:Long
    ): Unit = patientService.deletePatientById(id)


