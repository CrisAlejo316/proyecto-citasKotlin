package com.citas.citas.domains.responses

import java.time.Instant

data class PatientResponse (

        val idPaciente: Long,
        val nombre: String,
        val apellido: String,
        val identificacion: String,
        val telefono: Long,
        val created_at: Instant,
)