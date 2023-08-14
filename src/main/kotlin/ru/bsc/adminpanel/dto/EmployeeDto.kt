package ru.bsc.adminpanel.dto

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

data class EmployeeDto(
    val id: Long,
    val username: String,
    val email: String,
    val password: String
) {
    fun comparePassword(password: String): Boolean = BCryptPasswordEncoder().matches(password, this.password)
}
