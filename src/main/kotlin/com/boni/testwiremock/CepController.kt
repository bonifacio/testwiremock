package com.boni.testwiremock

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CepController(val client: CepClient) {

    @GetMapping
    fun get(): Address = client.get()
}