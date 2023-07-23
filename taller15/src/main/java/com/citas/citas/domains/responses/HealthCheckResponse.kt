package com.citas.citas.domains.responses

import org.springframework.http.HttpStatus

data class HealthCheckResponse(
    val statusOk: String = HttpStatus.OK.name
)