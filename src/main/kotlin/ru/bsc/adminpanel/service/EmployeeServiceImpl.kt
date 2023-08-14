package ru.bsc.adminpanel.service

import org.springframework.stereotype.Service
import ru.bsc.adminpanel.dto.EmployeeDto
import ru.bsc.adminpanel.model.EmployeeEntity
import ru.bsc.adminpanel.repository.EmployeeRepository
import java.lang.RuntimeException

@Service
class EmployeeServiceImpl(private val employeeRepository: EmployeeRepository) : EmployeeService {
    override fun getAll(): List<EmployeeDto> = employeeRepository.findAll().map { it.toDto() }

    override fun getById(id: Long): EmployeeDto = employeeRepository.findById(id)?.toDto() ?: throw RuntimeException(
        "User with id = $id not found"
    )

    override fun findByUserName(username: String): EmployeeDto =
        employeeRepository.findByUserName(username)?.toDto() ?: throw RuntimeException(
            "User with username = $username not found"
        )

    override fun create(dto: EmployeeDto): Long {
        val res = employeeRepository.create(dto)
        if (res == 0L) {
            throw RuntimeException("Something comes wrong in create employee, returned 0")
        }
        return res
    }


    override fun update(id: Long, dto: EmployeeDto) {
        employeeRepository.update(id, dto)
    }

    private fun EmployeeEntity.toDto() = EmployeeDto(
        id = id,
        username = username,
        email = email,
        password = password
    )


}