package com.example.enterpriseExam.controller

import com.example.enterpriseExam.NewUserInfo
import com.example.enterpriseExam.model.AuthorityEntity
import com.example.enterpriseExam.model.UserEntity
import com.example.enterpriseExam.service.AuthorityService
import com.example.enterpriseExam.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI

@RestController
@RequestMapping("/api")
class AuthController(@Autowired private val userService: UserService,
                     @Autowired private val authorityService: AuthorityService
) {

    @GetMapping("/authentication/all")
    fun getAuthorities(): ResponseEntity<List<AuthorityEntity>> {
        return ResponseEntity.ok().body(authorityService.getAuthorities())
    }

    @GetMapping("/user")
    fun getUsers(): ResponseEntity<List<UserEntity>> {
        return ResponseEntity.ok().body(userService.getUsers())
    }

    @PostMapping("/register")
    fun registerUser(@RequestBody newUserInfo: NewUserInfo): ResponseEntity<UserEntity>{
        val createdUser = userService.registerUser(newUserInfo)
        val uri = URI.create(
            ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/register/authentication").toUriString()
        )
        return ResponseEntity.created(uri).body(createdUser)
    }

    @PutMapping("/user/edituser/{id}")
    fun updateAuthority(@PathVariable("id") id: Long, @RequestBody anotherAuthority: AuthorityEntity): ResponseEntity<AuthorityEntity> {
        return ResponseEntity.ok().body(authorityService.updateAuthority(id, anotherAuthority))
    }
}