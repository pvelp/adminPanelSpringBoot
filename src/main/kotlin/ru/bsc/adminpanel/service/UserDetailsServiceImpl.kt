package ru.bsc.adminpanel.service

import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import ru.bsc.adminpanel.repository.UserRepository
import java.lang.RuntimeException

@Service
class UserDetailsServiceImpl(private val userRepository: UserRepository): UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByName(username) ?: throw RuntimeException("User with name = $username not found")
        return User(user.name, user.password, null)
    }
}