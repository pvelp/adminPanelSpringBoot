package ru.bsc.adminpanel

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication
import ru.bsc.adminpanel.dto.ClientDto

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class AdminPanelApplication

fun main(args: Array<String>) {
    runApplication<AdminPanelApplication>(*args)
}
