package com.boni.testwiremock

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class TestwiremockApplication

fun main(args: Array<String>) {
	runApplication<TestwiremockApplication>(*args)
}