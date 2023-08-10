package ru.bsc.adminpanel.controller

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
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
@Api(description = "Контроллер для управления клиентами")
class ClientController(private val clientService: ClientService) {
    @GetMapping
    @ApiOperation("Получение всех клиентов")
    fun getAll(): List<ClientDto> = clientService.getAll()

    @GetMapping("/{id}")
    @ApiOperation("Получения клиента по id")
    fun getById(@PathVariable id: Long): ClientDto = clientService.getById(id)

    @PostMapping
    @ApiOperation("Создание клиента")
    fun create(@RequestBody dto: ClientDto): Long = clientService.create(dto)

    @PutMapping("/{id}")
    @ApiOperation("Обновление клиента")
    fun update(@PathVariable id: Long, @RequestBody dto: ClientDto) {
        clientService.update(id, dto)
    }

    @GetMapping("/{telegramId}")
    @ApiOperation("Получение клиента по его Telegram id")
    fun getByTelegramId(@PathVariable telegramId: String): ClientDto = clientService.getByTelegramId(telegramId)

    @PutMapping("/{telegramId}")
    @ApiOperation("Обновление клиента по его Telegram id")
    fun updateByTelegramId(@PathVariable telegramId: String, @RequestBody dto: ClientDto) {
        clientService.updateByTelegramId(telegramId, dto)
    }

    @GetMapping("/isBanned")
    @ApiOperation("Получение всех забаненных клиентов")
    fun getByIsBanned(): List<ClientDto> = clientService.getAllByIsBanned()

    @DeleteMapping("/{id}")
    @ApiOperation("Удаление клиента по его id")
    fun delete(@PathVariable id: Long) {
        clientService.deleteById(id)
    }

    @DeleteMapping("/{telegramId}")
    @ApiOperation("Удаление клиента по его Telegram id")
    fun deleteByTelegramId(@PathVariable telegramId: String) {
        clientService.deleteByTelegramId(telegramId)
    }

}