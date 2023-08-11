package ru.bsc.adminpanel.service

import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import ru.bsc.adminpanel.dto.UserDto
import ru.bsc.adminpanel.model.UserEntity
import ru.bsc.adminpanel.repository.UserRepository
import java.lang.RuntimeException

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {
    override fun getAll(): List<UserDto> = userRepository.findAll().map { it.toDto() }

    override fun getById(id: Long): UserDto = userRepository.findById(id)?.toDto() ?: throw RuntimeException(
        "User with id = $id not found"
    )

    override fun findByName(name: String): UserDto = userRepository.findByName(name)?.toDto() ?: throw RuntimeException(
    "User with name = $name not found"
    )

    override fun create(dto: UserDto): Long = userRepository.create(dto)


    override fun update(id: Long, dto: UserDto) {
        userRepository.update(id, dto)
    }

    private fun UserEntity.toDto() = UserDto(
        id = id,
        name = name,
        email = email,
        password = password
    )


}