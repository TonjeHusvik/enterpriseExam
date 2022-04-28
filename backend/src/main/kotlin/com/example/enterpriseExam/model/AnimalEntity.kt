package com.example.enterpriseExam.model

import javax.persistence.*

@Entity
@Table(name = "animals")
class AnimalEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "animals_animal_id_seq")
    @SequenceGenerator(
        name = "animals_animal_id_seq",
        allocationSize = 1
    )

    @Column(name = "animal_id")
    val id: Long? = null,

    @Column(name = "animal_name")
    var name: String,

    @Column(name = "animal_type")
    var animalType: String,

    @Column(name = "animal_breed")
    var breed: String,

    @Column(name = "animal_age")
    var age: Int,

    @Column(name = "animal_health")
    var health: String,

) {
    override fun toString(): String {
        return "AnimalEntity(name='$name', id=$id, animalType='$animalType', breed='$breed', age=$age, health='$health')"
    }
}