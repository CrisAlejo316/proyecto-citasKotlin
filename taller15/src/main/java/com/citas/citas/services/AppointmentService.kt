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
    private lateinit var patientService: PatientService

    @Autowired
    private lateinit var doctorService: DoctorService

    @Autowired
    private lateinit var appointmentRepository: AppointmentRepository

    fun createAppointment(request: CreateAppointmentRequest): String {
        try {
            var doctor = doctorService.getDoctorById(request.idDoctor)
            var patient = patientService.getPatientByIdentificacion(request.idPaciente)
            val especialidad = appointmentRepository.getDoctorEspecialidad(request.idDoctor)
            if (especialidad.equals(request.especialidad)) {
                val appointment = appointmentRepository.save(
                    Appointment(
                        horario = request.horario,
                        especialidad = request.especialidad,
                        doc = request.idDoctor,
                        idPac = request.idPaciente,
                    )
                )
                return "Appointment created successfully"
            }
            return "Ups, something was wrong"
        }catch(e: EmptyResultDataAccessException){
            throw Error("Patient not found")
        }catch(e: Error){
            throw Error(e.message)
        }
    }

    fun getAllAppointments(): List<Appointment>{
        var response = appointmentRepository.findAll()
        return response
    }

    fun getAppointmentById(id: Long): Appointment {
        var appointment = appointmentRepository.findByIdOrNull(id)
        if (appointment !== null){
            return appointment
        }
        throw Error("Appointment not found")
    }

    fun updateAppointment(id: Long): CreateAppointmentResponse{
        try{
            val appointment = getAppointmentById(id)
            appointment.horario = request.horario
            val updateAppointment = appointmentRepository.save(appointment)
            val doctor = doctorService.getDoctorById(updateAppointment.doc)
            return CreateAppointmentResponse(
                idPaciente = updateAppointment.idPac,
                horario = updateAppointment.horario,
                doctor = doctor.nombre,
                especialidad = updateAppointment.especialidad,
                consultorio = doctor.consultorio
            )
        } catch (e: Error){
            throw Error(e.message)
        }
    }

    fun deleteAppointment(id: Long): Unit{
       try {
            getAppointmentById(id)
            appointmentRepository.deleteAppointmentByById(id)
            return "El registro se ha borrado con exito"
        } catch (e: DataAccessException) {
            return "El registro se ha borrado con exito"
        } catch (e: Error) {
            return "El registro no existe en la base de datos"
        } 
    }
	
}