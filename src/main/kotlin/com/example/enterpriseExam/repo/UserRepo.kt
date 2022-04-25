package com.example.enterpriseExam.repo

import com.example.enterpriseExam.model.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepo: JpaRepository<UserEntity, Long> {
    fun findByEmail(email: String): UserEntity?
}