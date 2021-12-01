package com.boni.testwiremock

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(name= "cep", url = "\${feign.url}", configuration = [FeignConfig::class])
interface CepClient {

    @GetMapping("/ws/01001000/json")
    fun get(): Address
}
