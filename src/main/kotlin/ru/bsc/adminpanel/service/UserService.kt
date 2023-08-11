package ru.bsc.adminpanel.service

import ru.bsc.adminpanel.dto.ClientDto
import ru.bsc.adminpanel.dto.UserDto

interface UserService {
    fun getAll(): List<UserDto>
    fun getById(id: Long): UserDto
    fun create(dto: UserDto): Long
    fun update(id: Long, dto: UserDto)
    fun findByName(name: String): UserDto
}