package com.citas.citas.repositories

import com.citas.citas.domains.entity.Doctor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Repository
interface DoctorRepository : JpaRepository<Doctor, Long> {
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Query("select * from citas where id_doctor = :id", nativeQuery = true)
    fun getByDoctorId(id: Long): List<Doctor>

    @Query("delete from citas where id_doctor =:id", nativeQuery = true)
    fun deleteDoctorByIdDoctor(id: Long): Unit

    @Query("update doctor set especialidad = :especialidad where id_doctor = :id", nativeQuery = true)
    fun updateDoctorById(id: Long, especialidad: String): Unit

}