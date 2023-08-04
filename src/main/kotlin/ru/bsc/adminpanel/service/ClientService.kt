package ru.bsc.adminpanel.service

import ru.bsc.adminpanel.dto.ClientDto

interface ClientService {
    fun getAll(): List<ClientDto>
    fun getById(id: Long): ClientDto
    fun create(dto: ClientDto): Long
    fun update(id: Long, dto: ClientDto)
    fun deleteById(id: Long)
    fun getByTelegramId(telegramId: String): ClientDto
    fun deleteByTelegramId(telegramId: String)
    fun updateByTelegramId(telegramId: String, dto: ClientDto)
    fun getAllByIsBanned(): List<ClientDto>
}