package com.example.enterpriseExam.service

import com.example.enterpriseExam.NewUserInfo
import com.example.enterpriseExam.model.AuthorityEntity
import com.example.enterpriseExam.model.UserEntity
import com.example.enterpriseExam.repo.AuthorityRepo
import com.example.enterpriseExam.repo.UserRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(@Autowired private val userRepo: UserRepo,
                  @Autowired private val authorityRepo: AuthorityRepo): UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        username?.let{
            val user = userRepo.findByEmail(it)
            return User(user.email, user.password, user.authorities.map { authority -> SimpleGrantedAuthority(authority.authorityName) })
        }
        throw Exception("Cant find any users")
    }

    fun getUsers(): List<UserEntity>{
        return userRepo.findAll()
    }

    fun registerUser(newUserInfo: NewUserInfo): UserEntity{
        val newUser = UserEntity(email = newUserInfo.email, password = BCryptPasswordEncoder().encode(newUserInfo.password))
        newUser.authorities.add(getAuthority("USER"))
        return userRepo.save(newUser)
    }

    fun getAuthority(name: String): AuthorityEntity {
        return authorityRepo.getByAuthorityName(name)
    }

    fun getUserByEmail(email: String): UserEntity? {
        return userRepo.findByEmail(email)
    }

    /*fun grantAuthorityToUser(email: String, authorityName: String){
        val user = userRepo.findByEmail(email)
        val authority = authorityRepo.getByAuthorityName(authorityName)
        user.authorities.add(authority)
        userRepo.save(user)
    }*/

}