package ru.bsc.adminpanel.repository

import ru.bsc.adminpanel.dto.EmployeeDto
import ru.bsc.adminpanel.model.EmployeeEntity

interface EmployeeRepository : BaseRepository<EmployeeEntity, EmployeeDto> {
    override fun findAll(): List<EmployeeEntity>

    override fun create(dto: EmployeeDto): Long

    override fun findById(id: Long): EmployeeEntity?

    override fun deleteById(id: Long)

    override fun update(id: Long, dto: EmployeeDto)

    fun findByUserName(username: String): EmployeeEntity?
}