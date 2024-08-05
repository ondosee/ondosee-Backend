package com.ondosee

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication
class OndoseeApplication

fun main(args: Array<String>) {
	runApplication<OndoseeApplication>(*args)
}
