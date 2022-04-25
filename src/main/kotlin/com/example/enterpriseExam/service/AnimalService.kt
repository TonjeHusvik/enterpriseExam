package com.example.enterpriseExam.service

import com.example.enterpriseExam.model.AnimalEntity
import com.example.enterpriseExam.repo.AnimalRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AnimalService(@Autowired private val animalRepo: AnimalRepo) {

    fun getAnimals(): List<AnimalEntity>{
        return animalRepo.findAll()
    }

    fun getSingleAnimal(id: Long): AnimalEntity{
        return animalRepo.getById(id)
    }

    fun addNewAnimal(newAnimal: AnimalEntity): AnimalEntity {
        return animalRepo.save(newAnimal)
    }

    fun updateAnimal(id: Long, newAnimal: AnimalEntity): AnimalEntity {
        val updateAnimal: AnimalEntity = animalRepo.getById(id)

        updateAnimal.name = newAnimal.name
        updateAnimal.animalType = newAnimal.animalType
        updateAnimal.breed = newAnimal.breed
        updateAnimal.age = newAnimal.age
        updateAnimal.health = newAnimal.health

        return animalRepo.save(updateAnimal)
    }

    fun deleteAnimal(id: Long) {
        return if (animalRepo.existsById(id)) {
            animalRepo.deleteById(id)
        } else throw Exception("No matching animal found")
    }

}