package com.example.enterpriseExam.repo

import com.example.enterpriseExam.model.AnimalEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AnimalRepo: JpaRepository<AnimalEntity, Long> {

}