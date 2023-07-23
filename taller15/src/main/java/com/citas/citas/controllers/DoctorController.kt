package com.citas.citas.controllers

import org.springframework.web.bind.annotation.RestController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PathVariable
import io.micrometer.core.instrument.config.validate.Validated

@RestController
class DoctorController {
    @Autowired
    private lateinit var doctorService: DoctorService

    @GetMapping(Doctor)
    fun getAllDoctors(): List<Doctor> = doctorService.getAllDoctors()

    @PostMapping(CreateDoctors)
    fun createDoctor(
            @RequestBody @Validated request: CreateDoctorRequest
    ): CreateDoctorResponse = doctorService.createDoctor(request)

    @GetMapping(GetDoctorById)
    fun getDoctorById(
            @PathVariable("id") id: Long
    ): List<Doctor> = doctorService.getDoctorById(id)

    @PutMapping(UpdateDoctor)
    fun updateDoctor(
        @PathVariable("id") id: Long,
        @RequestBody @Validated request: CreateDoctorRequest
    ): String  = doctorService.updateDoctor(id, request)

    @DeleteMapping(DeleteDoctor)
    fun deleteDoctor(
            @PathVariable("id") id: Long
    ): String = doctorService.deleteDoctor(id)

}