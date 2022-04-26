package com.example.enterpriseExam.service

import com.example.enterpriseExam.model.AuthorityEntity
import com.example.enterpriseExam.model.UserEntity
import com.example.enterpriseExam.repo.AuthorityRepo
import com.example.enterpriseExam.repo.UserRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AuthorityService(@Autowired private val authorityRepo: AuthorityRepo,
                       @Autowired private val userRepo: UserRepo) {
    fun getAuthorities(): List<AuthorityEntity>{
        return authorityRepo.findAll()
    }

}