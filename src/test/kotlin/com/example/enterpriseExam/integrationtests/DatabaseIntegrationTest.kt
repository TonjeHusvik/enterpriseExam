package com.example.enterpriseExam.integrationtests

import com.example.enterpriseExam.NewUserInfo
import com.example.enterpriseExam.service.UserService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@Import(UserService::class) //maybe implement animalservice and authorityService

class DatabaseIntegrationTest(@Autowired private val userService: UserService) {

    @Test
    fun shouldGetUsers() {
        val result = userService.getUsers()
        assert(result.size == 1)
    }

    @Test
    fun registerAndFindUser() {
        val newUserInfo = NewUserInfo("ted@ted.ted", "ted")

        val createUser = userService.registerUser(newUserInfo)
        assert(createUser.email == "ted@ted.ted")

        val foundUser = userService.loadUserByUsername("ted@ted.ted")
        assert(createUser.email == foundUser.username)
    }

    @Test
    fun createUserThenGetByEmail() {
        val newUserInfo = NewUserInfo("george@ted.ted", "george")
        val createdUser = userService.registerUser(newUserInfo)
        assert(createdUser.email == "george@ted.ted")

        val foundUser = userService.getUserByEmail("george@ted.ted")
        assert(foundUser?.email == "george@ted.ted")
    }
}