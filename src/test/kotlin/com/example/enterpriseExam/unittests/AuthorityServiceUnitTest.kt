package com.example.enterpriseExam.unittests

import com.example.enterpriseExam.repo.AuthorityRepo
import com.example.enterpriseExam.service.AuthorityService
import io.mockk.mockk


class AuthorityServiceUnitTest {
    private val authorityRepo = mockk<AuthorityRepo>()
    private val authorityService = AuthorityService(authorityRepo)

    //TESTERS ARE COMING <3
}