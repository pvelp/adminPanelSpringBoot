package ru.bsc.adminpanel.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.bsc.adminpanel.dto.UserDto
import ru.bsc.adminpanel.service.UserService

@RestController
@RequestMapping("/")
class AuthController(private val userService: UserService) {

    @PostMapping("register")
    fun register(@RequestBody dto: UserDto): ResponseEntity<Long> {
        return ResponseEntity.ok(userService.create(dto))
    }


}