package com.boni.testwiremock

import feign.Logger
import org.springframework.context.annotation.Bean

class FeignConfig {

    @Bean
    fun feignLoggerLevel(): Logger.Level {
        return Logger.Level.FULL
    }
}