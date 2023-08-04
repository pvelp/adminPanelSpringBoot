package ru.bsc.adminpanel.controller

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.bsc.adminpanel.dto.ClientDto
import ru.bsc.adminpanel.service.ClientService

@RestController
@RequestMapping("/clients")
class ClientController(private val clientService: ClientService) {
    @GetMapping
    fun getAll(): List<ClientDto> = clientService.getAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ClientDto = clientService.getById(id)

    @PostMapping
    fun create(@RequestBody dto: ClientDto): Long = clientService.create(dto)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody dto: ClientDto) {
        clientService.update(id, dto)
    }

    @GetMapping("/{telegramId}")
    fun getByTelegramId(@PathVariable telegramId: String): ClientDto = clientService.getByTelegramId(telegramId)

    @PutMapping("/{telegramId}")
    fun updateByTelegramId(@PathVariable telegramId: String, @RequestBody dto: ClientDto) {
        clientService.updateByTelegramId(telegramId, dto)
    }

    @GetMapping("/isBanned")
    fun getByIsBanned(): List<ClientDto> = clientService.getAllByIsBanned()

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        clientService.deleteById(id)
    }

    @DeleteMapping("/{telegramId}")
    fun deleteByTelegramId(@PathVariable telegramId: String) {
        clientService.deleteByTelegramId(telegramId)
    }


}