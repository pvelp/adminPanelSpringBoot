package ru.bsc.adminpanel.service

import ru.bsc.adminpanel.dto.EmployeeDto

interface EmployeeService {
    fun getAll(): List<EmployeeDto>
    fun getById(id: Long): EmployeeDto
    fun create(dto: EmployeeDto): Long
    fun update(id: Long, dto: EmployeeDto)
    fun findByUserName(username: String): EmployeeDto

}