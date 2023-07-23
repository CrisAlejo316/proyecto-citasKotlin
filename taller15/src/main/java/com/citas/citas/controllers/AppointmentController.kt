package com.citas.citas.controllers

import com.citas.citas.constants.*
import com.citas.citas.domains.entity.Appointment
import com.citas.citas.domains.request.CreateAppointmentRequest
import com.citas.citas.domains.request.CreateDoctorRequest
import com.citas.citas.domains.responses.CreateAppointmentResponse
import com.citas.citas.domains.responses.HealthCheckResponse
import com.citas.citas.services.AppointmentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class AppointmentController {
    @Autowired
    private lateinit var appointmentService: AppointmentService
    @GetMapping(Appointment)

    fun getAllAppointments(): List<Appointment> = appointmentService.getAllAppointments()

    @PostMapping(CreateAppointments)
    fun createAppointment(
        @RequestBody request: CreateAppointmentRequest
    ): CreateAppointmentResponse = appointmentService.createAppointment(request)


    @GetMapping(GetAppointmentById)
    fun getAppointmentById(
        @PathVariable("id") id: Long
    ): List<Appointment> = appointmentService.getAppointmentById(id)
    @PutMapping(UpdateAppointment)

    fun updateAppointment(
        @PathVariable("id") id: Long
    ): CreateAppointmentResponse = appointmentService.updateAppointment(id)
}