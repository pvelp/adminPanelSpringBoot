package ru.bsc.adminpanel.repository

import ru.bsc.adminpanel.dto.UserDto
import ru.bsc.adminpanel.model.UserEntity

interface UserRepository :  BaseRepository<UserEntity, UserDto>{
    override fun findAll(): List<UserEntity>

    override fun create(dto: UserDto): Long

    override fun findById(id: Long): UserEntity?

    override fun deleteById(id: Long)

    override fun update(id: Long, dto: UserDto)

    fun findByName(name: String): UserEntity?
}