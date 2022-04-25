package com.example.enterpriseExam.service

import com.example.enterpriseExam.model.AuthorityEntity
import com.example.enterpriseExam.repo.AuthorityRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AuthorityService(@Autowired private val authorityRepo: AuthorityRepo) {
    fun getAuthorities(): List<AuthorityEntity>{
        return authorityRepo.findAll()
    }

}