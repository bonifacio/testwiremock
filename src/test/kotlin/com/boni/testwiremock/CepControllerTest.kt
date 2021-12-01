package com.boni.testwiremock

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@ContextConfiguration(initializers = [WireMockContextInitializer::class])
@AutoConfigureMockMvc
class CepControllerTest(@Autowired val mockMvc: MockMvc) {

    @Autowired
    private lateinit var wireMockServer: WireMockServer

        private fun stubResponse(url: String, responseBody: String, responseStatus: Int = HttpStatus.OK.value()) {
        wireMockServer.stubFor(
            WireMock.get(url)
                .willReturn(
                    WireMock.aResponse()
                        .withStatus(responseStatus)
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody(responseBody)
                )
        )
    }

    @AfterEach
    fun afterEach() {
        wireMockServer.resetAll()
    }

    @Test
    fun `teste usando wiremock`() {

        val responseBody = """
            {
              "cep": "01001-000",
              "logradouro": "Praça da Sé",
              "complemento": "lado ímpar",
              "bairro": "Sé",
              "localidade": "São Paulo",
              "uf": "SP",
              "ibge": "3550308",
              "gia": "1004",
              "ddd": "11",
              "siafi": "7107"
            }
        """.trimIndent()

        stubResponse(url ="/ws/01001000/json", responseBody = responseBody)

        mockMvc.perform(MockMvcRequestBuilders.get("/"))
            .andExpect(status().isOk)
            .andExpect(content().json("{\"bairro\":\"Sé\",\"logradouro\":\"Praça da Sé\"}"))
    }

}