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
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.*

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
    fun shouldGetAllAnimals() {
        val animal = AnimalEntity(name = "fido", animalType = "dog", breed = "chihuahua", age = 11, health =  "amazing!")
        every { animalService.getAnimals() } answers {
            mutableListOf(animal)
        }
        mockMvc.get("/api/shelter"){

        }
            .andExpect { status { isOk() } }
            .andExpect { content { contentType(APPLICATION_JSON) } }
    }

    @Test
    fun shouldGetAnimalById() {
        val animal = AnimalEntity(name = "bjarne", animalType = "bear", breed = "alaskan", age = 51, health =  "sleepy")
        every { animalService.getSingleAnimal(1) } answers {
            animal
        }
        mockMvc.get("/api/shelter/1"){

        }
            .andExpect { status { isOk() } }
            .andExpect { content { contentType(APPLICATION_JSON) } }
    }

    @Test
    fun shouldAddNewAnimal() {
        val animal = AnimalEntity(name = "missy", animalType = "deer", breed = "forest", age = 51, health =  "happy")
        every { animalService.addNewAnimal(any()) } answers {
            animal
        }
        mockMvc.post("/api/shelter/edit/newanimal"){
            contentType = APPLICATION_JSON
            content = "{\"name\":\"missy\", " +
                    "\"animalType\":\"deer\", " +
                    "\"breed\":\"forest\", " +
                    "\"age\": 51," +
                    "\"health\":\"happy\"}"
        }
            .andExpect { status { isOk() } }
            .andExpect { content { contentType(APPLICATION_JSON) } }
    }

    @Test
    fun shouldUpdateAnimal() {
        val animal = AnimalEntity(name = "motorboat", animalType = "deer", breed = "forest", age = 2, health =  "happy")
        every { animalService.updateAnimal(1, any()) } answers {
            animal
        }
        mockMvc.put("/api/shelter/edit/1"){
            contentType = APPLICATION_JSON
            content = "{\"name\":\"motorboat\", " +
                    "\"animalType\":\"deer\", " +
                    "\"breed\":\"forest\", " +
                    "\"age\": 3," +
                    "\"health\":\"was shot last week\"}"
        }
            .andExpect { status { isOk() } }
            .andExpect { content { contentType(APPLICATION_JSON) } }
    }

    @Test
    fun shouldDeleteAnimal() {
        val animal = AnimalEntity(name = "juice", animalType = "rat", breed = "english rat", age = 51, health =  "awful")
        every { animalService.deleteAnimal(1) } answers {
            animal
        }
        mockMvc.delete("/api/shelter/edit/1"){
        }
            .andExpect { status { isOk() } }
    }
}