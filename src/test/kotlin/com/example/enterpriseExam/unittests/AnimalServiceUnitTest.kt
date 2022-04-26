package com.example.enterpriseExam.unittests

import com.example.enterpriseExam.model.AnimalEntity
import com.example.enterpriseExam.repo.AnimalRepo
import com.example.enterpriseExam.service.AnimalService
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

class AnimalServiceUnitTest {
    private val animalRepo = mockk<AnimalRepo>()
    private val animalService = AnimalService(animalRepo)

    @Test
    fun getAnimalsTest() {
        val animal1 = AnimalEntity(name = "Pepsi", animalType = "Cat", breed = "Maine Coon", age = 12, health =  "Fine")
        val animal2 = AnimalEntity(name = "Henry", animalType = "Hamster", breed = "Irish disaster", age = 2, health =  "Makes weird noises")

        every { animalRepo.findAll() } answers {
            mutableListOf(animal1, animal2)
        }

        val users = animalService.getAnimals()
        assert(users.size == 2)
    }

    @Test
    fun shouldRegisterNewAnimal() {
        // mocking out responses from save())
        every { animalRepo.save(any()) }  answers {
            // returns whatever we pass to it
            firstArg()
        }

        val createUser = animalService.addNewAnimal(AnimalEntity(name = "Barbie", animalType = "Pig", breed = "Mock pig", age = 7, health =  "Smells bad"))

        assert(createUser.name == "Barbie")
        assert(createUser.animalType == "Pig")
        assert(createUser.breed == "Mock pig")
    }
}