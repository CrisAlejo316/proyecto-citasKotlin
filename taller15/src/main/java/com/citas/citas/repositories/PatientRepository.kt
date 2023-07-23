package com.citas.citas.repositories

import com.citas.citas.domains.entity.Patient
import org.springframework.transaction.annotation.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Propagation

@Repository
interface PatientRepository: JpaRepository<Patient, Long> {
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Query("select * from pacientes where id_paciente = :id", nativeQuery = true)
    fun getPatientById(id:Long): List<Patient>

    @Query("delete from pacientes where id_paciente =:id", nativeQuery = true)
    fun deletePatientById(id:Long):String

    @Query("update pacientes set nombre=:nombre, apellido:=apellido, identificacion:=identificacion, telefono:=telefono where id_paciente = :id", nativeQuery = true)
    fun updatePatientById(id: Long, nombre: String, apellido: String, identificacion:String, telefono:Long): Unit
}