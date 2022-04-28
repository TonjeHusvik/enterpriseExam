package com.example.enterpriseExam.controller

import com.example.enterpriseExam.model.AnimalEntity
import com.example.enterpriseExam.service.AnimalService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class AnimalController(@Autowired private val animalService: AnimalService) {

    @GetMapping("/shelter")
    fun getAnimals(): ResponseEntity<List<AnimalEntity>> {
        return ResponseEntity.ok().body(animalService.getAnimals())
    }

    @GetMapping("/shelter/{id}")
    fun getSingleAnimal(@PathVariable("id") id: Long): ResponseEntity<AnimalEntity> {
        return ResponseEntity.ok().body(animalService.getSingleAnimal(id))
    }

    @PostMapping("/shelter/edit/newanimal")
    fun addNewAnimal(@RequestBody newAnimal: AnimalEntity): AnimalEntity {
        return animalService.addNewAnimal(newAnimal)
    }

    @PutMapping("/shelter/edit/{id}")
    fun updateAnimal(@PathVariable("id") id: Long, @RequestBody newAnimal: AnimalEntity): ResponseEntity<AnimalEntity> {
        return ResponseEntity.ok().body(animalService.updateAnimal(id, newAnimal))
    }

    @DeleteMapping("/shelter/edit/{id}")
    fun deleteAnimal(@PathVariable("id") id: Long) {
        return animalService.deleteAnimal(id)
    }
}