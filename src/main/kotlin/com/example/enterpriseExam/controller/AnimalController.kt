package com.example.enterpriseExam.controller

import com.example.enterpriseExam.model.AnimalEntity
import com.example.enterpriseExam.service.AnimalService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class AnimalController(@Autowired private val animalService: AnimalService) {

    //GETS ALL - WORKS
    @GetMapping("/shelter/all")
    fun getAnimals(): ResponseEntity<List<AnimalEntity>> {
        return ResponseEntity.ok().body(animalService.getAnimals())
    }

    //GET ONE BY ID - WORKS
    @GetMapping("/shelter/{id}")
    fun getSingleAnimal(@PathVariable("id") id: Long): ResponseEntity<AnimalEntity> {
        return ResponseEntity.ok().body(animalService.getSingleAnimal(id))
    }

    //ADD NEW ANIMAL - WORKS
    @PostMapping("/shelter/all")
    fun addNewAnimal(@RequestBody newAnimal: AnimalEntity): AnimalEntity {
        return animalService.addNewAnimal(newAnimal)
    }

    //UPDATE ONE ANIMAL BY ID
    @PutMapping("/shelter/all/{id}")
    fun updateAnimal(@PathVariable("id") id: Long, @RequestBody newAnimal: AnimalEntity): ResponseEntity<AnimalEntity> {
        return ResponseEntity.ok().body(animalService.updateAnimal(id, newAnimal))
    }

    //DELETE ONE ANIMAL BY ID
    @DeleteMapping("/shelter/all/{id}")
    fun deleteAnimal(@PathVariable("id") id: Long) {
        return animalService.deleteAnimal(id)
    }
}