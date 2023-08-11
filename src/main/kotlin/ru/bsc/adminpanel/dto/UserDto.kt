package ru.bsc.adminpanel.dto

data class UserDto(
    val id: Long,
    val name: String,
    val email: String,
    val password: String
)