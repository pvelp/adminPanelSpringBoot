package ru.bsc.adminpanel.model

import org.springframework.data.annotation.Id

class ClientEntity(
    @Id
    val id: Long,
    val telegramId: String,
    val name: String,
    val username: String,
    val phone: String,
    val birthday: String,
    val isBanned: Boolean,
)