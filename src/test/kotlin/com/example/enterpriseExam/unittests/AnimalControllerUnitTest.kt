package com.example.enterpriseExam.unittests

import com.example.enterpriseExam.model.AnimalEntity
import com.example.enterpriseExam.service.AnimalService
import com.example.enterpriseExam.service.AuthorityService
import com.example.enterpriseExam.service.UserService
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@WebMvcTest
@AutoConfigureMockMvc(addFilters = false)
class AnimalControllerUnitTest {
    @TestConfiguration
    class ControllerTestConfig {

        @Bean
        fun userService() = mockk<UserService>()

        @Bean
        fun authorityService() = mockk<AuthorityService>()

        @Bean
        fun animalService() = mockk<AnimalService>()
    }

    @Autowired
    private lateinit var animalService: AnimalService

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun shouldGetAllUsers() {
        val animal = AnimalEntity(name = "Fido", animalType = "Dog", breed = "Chihuahua", age = 11, health =  "amazing!")
        every { animalService.getAnimals() } answers {
            mutableListOf(animal)
        }
        mockMvc.get("/api/shelter"){

        }
            .andExpect { status { isOk() } }
            .andExpect { content { contentType(MediaType.APPLICATION_JSON) } }
    }
}