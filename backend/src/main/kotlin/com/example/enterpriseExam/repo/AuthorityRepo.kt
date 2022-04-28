package com.example.enterpriseExam.repo

import com.example.enterpriseExam.model.AuthorityEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorityRepo : JpaRepository<AuthorityEntity, Long> {

    fun getByAuthorityName(authorityName: String): AuthorityEntity
}