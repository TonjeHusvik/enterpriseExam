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
        val animal1 = AnimalEntity(name = "pepsi", animalType = "cat", breed = "maine coon", age = 12, health =  "fine")
        val animal2 = AnimalEntity(name = "henry", animalType = "hamster", breed = "irish disaster", age = 2, health =  "makes weird noises")

        every { animalRepo.findAll() } answers {
            mutableListOf(animal1, animal2)
        }

        val animals = animalService.getAnimals()
        assert(animals.size == 2)
    }

    @Test
    fun getAnimalByIdTest() {
        val animal = AnimalEntity(name = "couch", animalType = "dog", breed = "labrador", age = 10, health =  "super")
        every { animalRepo.getById(1)} answers {
            animal
        }

        val singleAnimal = animalService.getSingleAnimal(1)
        assert(singleAnimal.name == "couch")
        assert(singleAnimal.animalType == "dog")
    }


    @Test
    fun shouldAddNewAnimal() {
        every { animalRepo.save(any()) }  answers {
            firstArg()
        }

        val addAnimal = animalService.addNewAnimal(AnimalEntity(name = "barbie", animalType = "pig", breed = "mock pig", age = 7, health =  "smells bad"))

        assert(addAnimal.name == "barbie")
        assert(addAnimal.animalType == "pig")
        assert(addAnimal.breed == "mock pig")
        assert(addAnimal.age == 7)
        assert(addAnimal.health == "smells bad")
    }

    @Test
    fun shouldUpdateAnimal() {
        val animal = AnimalEntity(id = 1, name = "diaper", animalType = "cow", breed = "spotted christian", age = 7, health =  "bad")
        every { animalRepo.getById(any()) }  answers {
            animal
        }
        every { animalRepo.save(any()) }  answers {
            animal
        }

        val editedAnimal = animalService.updateAnimal(
            1, AnimalEntity(id = 1, name = "diaper", animalType = "cow", breed = "spotted christian", age = 8, health =  "worse"))
        assert(editedAnimal.name == "diaper")
        assert(editedAnimal.animalType == "cow")
        assert(editedAnimal.breed == "spotted christian")
        assert(editedAnimal.age == 8)
        assert(editedAnimal.health == "worse")
    }

    @Test
    fun shouldDeleteAnimal() {
        val animal = AnimalEntity(id = 1, name = "bull", animalType = "bull", breed = "black dark", age = 8, health =  "smells worse")
        every { animalRepo.deleteById(any()) }  answers {
            animal
        }

        animalService.deleteAnimal(1)
    }
}