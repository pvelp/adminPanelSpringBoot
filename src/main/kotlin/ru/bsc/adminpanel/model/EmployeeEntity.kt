package ru.bsc.adminpanel.model

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class EmployeeEntity(
    val id: Long,
    val username: String,
    val email: String,
    var password: String
)


