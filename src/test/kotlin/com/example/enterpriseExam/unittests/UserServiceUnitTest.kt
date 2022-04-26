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
        val userJoe = UserEntity(email = "joe@bob.com", password = "pirate")
        val userJim = UserEntity(email = "jim@bob.com", password = "pirate")

        // mocking out responses from findAll() in getUsers()
        every { userRepo.findAll() } answers {
            mutableListOf(userJoe, userJim)
        }

        val users = userService.getUsers()
        assert(users.size == 2)
        assert(users.first { it.email == "jim@bob.com" }.password == "pirate")
    }

    @Test
    fun shouldRegisterNewUser() {
        // mocking out responses from save() (doing this for all functions in registerUser())
        every { userRepo.save(any()) }  answers {
            // returns whatever we pass to it
            firstArg()
        }

        // mocking out responses from getByAuthorityName()
        every { authorityRepo.getByAuthorityName(any()) } answers {
            AuthorityEntity(authorityName = "USER")
        }

        val createUser = userService.registerUser(NewUserInfo("dumb@ass.com", "beaniebaby"))

        assert(createUser.email == "dumb@ass.com")
        assert(createUser.enabled)
    }
}