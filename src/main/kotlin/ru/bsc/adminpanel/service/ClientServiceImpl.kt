package ru.bsc.adminpanel.service

//import org.slf4j.Logger
//import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ru.bsc.adminpanel.dto.ClientDto
import ru.bsc.adminpanel.model.ClientEntity
import ru.bsc.adminpanel.repository.ClientRepository
import java.lang.RuntimeException

@Service
class ClientServiceImpl(private val clientRepository: ClientRepository) : ClientService {
//    private val logger = LoggerFactory.getLogger(ClientService::class.java)
    override fun getAll(): List<ClientDto> = clientRepository.getAll().map { it.toDto() }

    override fun getById(id: Long): ClientDto =
        clientRepository.findById(id)?.toDto() ?: throw RuntimeException("Client with id = $id not found")

    override fun create(dto: ClientDto): Long = clientRepository.create(dto)

    override fun update(id: Long, dto: ClientDto) {
        clientRepository.update(id, dto)
    }

    override fun deleteById(id: Long) {
        clientRepository.deleteById(id)
    }

    override fun getByTelegramId(telegramId: String): ClientDto = clientRepository.findByTelegramId(telegramId)?.toDto()
        ?: throw RuntimeException("Client with telegramId = $telegramId not found")

    override fun deleteByTelegramId(telegramId: String) {
        clientRepository.deleteByTelegramId(telegramId)
    }

    override fun updateByTelegramId(telegramId: String, dto: ClientDto) {
        clientRepository.updateByTelegramId(telegramId, dto)
    }

    override fun getAllByIsBanned(): List<ClientDto> = clientRepository.findAllByisBanned().map { it.toDto() }


    private fun ClientEntity.toDto() = ClientDto(
        id = id,
        telegramId = telegramId,
        name = name,
        username = username,
        phone = phone,
        birthday = birthday,
        isBanned = isBanned,
    )
}