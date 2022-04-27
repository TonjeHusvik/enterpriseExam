package com.example.enterpriseExam.integrationtests

import com.example.enterpriseExam.NewUserInfo
import com.example.enterpriseExam.model.AnimalEntity
import com.example.enterpriseExam.service.AnimalService
import com.example.enterpriseExam.service.AuthorityService
import com.example.enterpriseExam.service.UserService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@Import(UserService::class, AnimalService::class, AuthorityService::class)

class DatabaseIntegrationTest(@Autowired private val userService: UserService,
                              @Autowired private val animalService: AnimalService) {

    @Test
    @DirtiesContext
    fun shouldGetUsers() {
        val result = userService.getUsers()
        assert(result.size == 2)
    }

    @Test
    @DirtiesContext
    fun registerAndFindUser() {
        val newUserInfo = NewUserInfo("tester@tester.com", "password")

        val createUser = userService.registerUser(newUserInfo)
        assert(createUser.email == "tester@tester.com")

        val foundUser = userService.loadUserByUsername("tester@tester.com")
        assert(createUser.email == foundUser.username)
    }

    @Test
    @DirtiesContext
    fun createUserThenGetByEmail() {
        val newUserInfo = NewUserInfo("tester@tester.com", "password")
        val createdUser = userService.registerUser(newUserInfo)
        assert(createdUser.email == "tester@tester.com")

        val foundUser = userService.getUserByEmail("tester@tester.com")
        assert(foundUser?.email == "tester@tester.com")
    }

    @Test
    @DirtiesContext
    fun createAndFindUserTest(){
        userService.registerUser(NewUserInfo("tester@tester.com", "password"))
        val createdUser = userService.loadUserByUsername("tester@tester.com")

        assert(createdUser.username == "tester@tester.com")
        assert(createdUser.authorities.first().authority == "USER")
    }

    @Test
    @DirtiesContext
    fun shouldGetAnimals() {
        val result = userService.getUsers()
        assert(result.size == 2)
    }

    @Test
    @DirtiesContext
    fun createAnimal() {
        val newAnimal = AnimalEntity(id = 3, name = "lassi", animalType = "cat", breed = "we cant seem to figure it out", age = 13, health = "ok")
        val createdUser = animalService.addNewAnimal(newAnimal)
        assert(createdUser.name == "lassi")
        assert(createdUser.animalType == "cat")
    }

    @Test
    @DirtiesContext
    fun createAnimalThenGetById() {
        val newAnimal = AnimalEntity(id = 3, name = "pepsi", animalType = "cat", breed = "maine coon", age = 12, health = "fine")
        val createdUser = animalService.addNewAnimal(newAnimal)
        assert(createdUser.name == "pepsi")

        val foundUser = animalService.getSingleAnimal(3)
        assert(foundUser.name == "pepsi")
    }

}