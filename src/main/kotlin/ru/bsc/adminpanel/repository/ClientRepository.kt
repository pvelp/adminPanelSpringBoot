package ru.bsc.adminpanel.repository

import ru.bsc.adminpanel.dto.ClientDto
import ru.bsc.adminpanel.model.ClientEntity

interface ClientRepository {
    fun getAll(): List<ClientEntity>
    fun create(dto: ClientDto): Long
    fun getById(id: Long): ClientEntity?
    fun update(id: Long, dto: ClientDto)
    fun deleteById(id: Long)

    fun findByTelegramId(telegramId: String): ClientEntity?
    fun deleteByTelegramId(telegramId: String)
    fun updateByTelegramId(telegramId: String, dto: ClientDto)
    fun findAllByisBanned(): List<ClientEntity>
}