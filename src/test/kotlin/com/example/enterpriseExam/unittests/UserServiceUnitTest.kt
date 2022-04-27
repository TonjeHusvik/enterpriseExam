package com.example.enterpriseExam.unittests

import com.example.enterpriseExam.NewUserInfo
import com.example.enterpriseExam.model.AuthorityEntity
import com.example.enterpriseExam.model.UserEntity
import com.example.enterpriseExam.repo.AuthorityRepo
import com.example.enterpriseExam.repo.UserRepo
import com.example.enterpriseExam.service.UserService
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

class UserServiceUnitTest {
    private val userRepo = mockk<UserRepo>()
    private val authorityRepo = mockk<AuthorityRepo>()
    private val userService = UserService(userRepo, authorityRepo)

    @Test
    fun getUsersTest() {
        val userJoe = UserEntity(email = "test@test.com", password = "password")
        val userJim = UserEntity(email = "test2@test.com", password = "password")

        every { userRepo.findAll() } answers {
            mutableListOf(userJoe, userJim)
        }

        val users = userService.getUsers()
        assert(users.size == 2)
        assert(users.first { it.email == "test@test.com" }.password == "password")
    }

    @Test
    fun shouldRegisterNewUser() {
        every { userRepo.save(any()) }  answers {
            firstArg()
        }

        every { authorityRepo.getByAuthorityName(any()) } answers {
            AuthorityEntity(authorityName = "USER")
        }

        val createUser = userService.registerUser(NewUserInfo("test@test.com", "password"))

        assert(createUser.email == "test@test.com")
        assert(createUser.enabled)
    }

    @Test
    fun getUserByEmailTest() {
        val user = UserEntity(email = "test@test.com", password = "password")

        every { userRepo.findByEmail("test@test.com") } answers {
            user
        }

        val users = userService.getUserByEmail("test@test.com")
        assert(users?.email == "test@test.com")

    }


}