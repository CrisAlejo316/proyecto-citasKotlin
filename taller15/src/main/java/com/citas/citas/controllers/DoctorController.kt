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
    fun createDoctor(@RequestBody @Validated request: CreateDoctorRequest): ResponseEntity<DoctorResponse> {
        return try {
            ResponseEntity(doctorService.createDoctor(request), HttpStatus.CREATED)
        } catch (e: Error) {
            ResponseEntity(DoctorResponse(message=e.message), HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping(GetDoctorById)
    fun getDoctorById(@PathVariable("id") id: Long): DoctorResponse {
        return try{
            DoctorResponse("Doctor $id found", doctorService.getDoctorById(id))
        }catch(e: Error){
            DoctorResponse(e.message)
        }
    }

    @PutMapping(UpdateDoctor)
    fun updateDoctor(
        @PathVariable("id") id: Long,
        @RequestBody @Validated request: UpdateDoctorRequest
    ): ResponseEntity<DoctorResponse> {
        return try{
            ResponseEntity(doctorService.updateDoctor(id, request), HttpStatus.ACCEPTED)
        }catch (e: Error){
            ResponseEntity(DoctorResponse(message=e.message), HttpStatus.BAD_REQUEST)
        }
    }

     @DeleteMapping(DeleteDoctor)
    fun deleteDoctor(@PathVariable("id") id: Long): ResponseEntity<DoctorResponse> {
        return try{
            ResponseEntity(doctorService.deleteDoctor(id), HttpStatus.ACCEPTED)
        }catch (e: Error){
            ResponseEntity(DoctorResponse(message=e.message), HttpStatus.BAD_REQUEST)
        }
    }

}