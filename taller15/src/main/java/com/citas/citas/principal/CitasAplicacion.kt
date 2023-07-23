package com.citas.citas.principal

import org.springframework.boot.autoconfigure.SpringBootApplication

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication


@SpringBootApplication()
@EnableJpaRespositories("com.citas.citas.repositories")
@EntityScan("com.citas.citas.domains.entity")
class CitasAplicacion 

	fun main(args: Array<String>) {
	runApplication<CitasAplicacion>(*args)
}