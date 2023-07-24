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
	
    @GetMapping(Appointment
    fun getAllAppointments(): List<Appointment> = appointmentService.getAllAppointments()

    @PostMapping(CreateAppointments)
    fun createAppointment(
        @RequestBody request: CreateAppointmentRequest
    ): ResponseEntity<String> {
        try{
            return ResponseEntity(appointmentService.createAppointment(request), HttpStatus.OK)
        }catch(e: Error){
            return ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
        }
    }


     @GetMapping(GetAppointmentById)
    fun getAppointmentById(@PathVariable("id") id: Long): ResponseEntity<Any>{
        try{
            return ResponseEntity(appointmentService.getAppointmentById(id), HttpStatus.OK)
        }catch (e: Error){
            return ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
        }
    }
		
		
    @PutMapping(UpdateAppointment)
    fun updateAppointment(
        @PathVariable("id") id: Long,
        @RequestBody request: UpdateAppointmentRequest
    ): ResponseEntity<Any>  {
        try{
            return ResponseEntity(appointmentService.updateAppointment(id, request), HttpStatus.OK)
        }catch(e: Error){
            return ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
        }
    }
		
	@DeleteMapping(DeleteAppointment)
    fun deleteAppointment(@PathVariable("id") id: Long): ResponseEntity<String> {
        return try{
            ResponseEntity(appointmentService.deleteAppointment(id), HttpStatus.ACCEPTED)
        }catch (e: Error){
            ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
        }
    }
		
}