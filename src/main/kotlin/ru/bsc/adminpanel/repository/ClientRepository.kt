package ru.bsc.adminpanel.repository

import ru.bsc.adminpanel.dto.ClientDto
import ru.bsc.adminpanel.model.ClientEntity

interface ClientRepository : BaseRepository<ClientEntity, ClientDto> {
    override fun findAll(): List<ClientEntity>
    override fun create(dto: ClientDto): Long
    override fun findById(id: Long): ClientEntity?
    override fun update(id: Long, dto: ClientDto)
    override fun deleteById(id: Long)

    fun findByTelegramId(telegramId: String): ClientEntity?
    fun deleteByTelegramId(telegramId: String)
    fun updateByTelegramId(telegramId: String, dto: ClientDto)
    fun findAllByisBanned(): List<ClientEntity>
}