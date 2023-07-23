package com.citas.citas.domains.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name="citas")
open class Appointment (
        @Id
        @Column(name=" id_cita")
        open var idcita: Long=0,

        @Column(name="horario")
        open var horario: String,

        @Column(name="especialidad")
        open var especialidad: String,

        @Column(name="id_doctor")
        open var doc: Long,

        @Column(name="identificacion_paciente")
        open var idPac: String,


)