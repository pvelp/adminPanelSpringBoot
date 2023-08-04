package ru.bsc.adminpanel.dto

data class ClientDto(
    val id: Long,
    val telegramId: String,
    val name: String,
    val username: String,
    val phone: String,
    val birthday: String,
    var isBanned: Boolean,
)
