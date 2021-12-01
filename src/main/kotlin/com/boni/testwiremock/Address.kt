package com.boni.testwiremock

import com.fasterxml.jackson.annotation.JsonProperty

data class Address(
    @JsonProperty("logradouro") val street: String,
    @JsonProperty("bairro") val district: String
)
