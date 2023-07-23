package com.citas.citas.services

import com.citas.citas.domains.entity.Appointment
import com.citas.citas.domains.entity.Doctor
import com.citas.citas.domains.request.CreateAppointmentRequest
import com.citas.citas.domains.responses.CreateAppointmentResponse
import com.citas.citas.repositories.AppointmentRepository
import com.citas.citas.repositories.DoctorRepository
import com.citas.citas.repositories.PatientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class AppointmentService {
    @Autowired
    private lateinit var appointmentRepository: AppointmentRepository

    @Autowired
    private lateinit var patientRepository: PatientRepository

    fun createAppointment(request: CreateAppointmentRequest): CreateAppointmentResponse{

        val especialidad = appointmentRepository.getDoctorpecialidad(request.idDoctor)

        if (especialidad.equals(request.especialidad)){
            print(request.idPaciente)
            val appointment = appointmentRepository.save(
                Appointment(
                    horario = request.horario,
                    especialidad = request.especialidad,
                    doc = request.idDoctor,
                    idPac = request.idPaciente,
                )
            )
            return CreateAppointmentResponse(
                idPaciente = request.idPaciente,
                especialidad = request.especialidad,
                doctor = "Carlos",
                consultorio = 101,
                horario = request.horario
            )
        }
        return CreateAppointmentResponse(
            idPaciente = "1",
            especialidad = request.especialidad,
            doctor = "Carlos",
            consultorio = 101,
            horario = "16"
        )
    }

    fun getAllAppointments(): List<Appointment>{
        var response = appointmentRepository.findAll()
        return response
    }

    fun getAppointmentById(id: Long): List<Appointment>{
        var citas = appointmentRepository.getAppointmentById(id)
        return citas
    }

    fun updateAppointment(id: Long): CreateAppointmentResponse{
        return CreateAppointmentResponse(
            idPaciente = "1",
            especialidad = "Test",
            doctor = "Carlos",
            consultorio = 101,
            horario = "16"
        )
    }

    fun deleteAppointment(id: Long): Unit{
        var delete = appointmentRepository.deleteAppointmentByById(id)
    }
}