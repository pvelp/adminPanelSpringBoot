package ru.bsc.adminpanel.model

import org.springframework.data.annotation.Id

class UserEntity(
    @Id
    val id: Long,
    val name: String,
    val email: String,
    var password: String = ""
)

