package ru.bsc.adminpanel.repository

import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Repository
import ru.bsc.adminpanel.dto.ClientDto
import ru.bsc.adminpanel.model.ClientEntity
import java.sql.ResultSet

@Repository
class ClientRepositoryImpl(
    private val jdbcTemplate: NamedParameterJdbcTemplate
) : ClientRepository {
    override fun findAll(): List<ClientEntity> =
        jdbcTemplate.query("SELECT * FROM clients ORDER BY username", ROW_MAPPER)


    override fun create(dto: ClientDto): Long {
        val keyHolder = GeneratedKeyHolder()
        jdbcTemplate.update(
            "INSERT INTO clients (telegram_id, name, username, phone, birthday, is_banned)" +
                    " VALUES (:telegram_id, :name, :username, :phone, :birthday, :is_banned)",
            MapSqlParameterSource(
                mapOf(
                    "telegram_id" to dto.telegramId,
                    "name" to dto.name,
                    "username" to dto.username,
                    "phone" to dto.phone,
                    "birthday" to dto.birthday,
                    "is_banned" to dto.isBanned,
                )
            ),
            keyHolder,
            listOf("id").toTypedArray()
        )
        return keyHolder.key?.toLong() ?: 0
    }


    override fun findById(id: Long): ClientEntity? =
        jdbcTemplate.query("SELECT * FROM clients WHERE id=?", mapOf("id" to id), ROW_MAPPER).firstOrNull()


    override fun update(id: Long, dto: ClientDto) {
        jdbcTemplate.update(
            "UPDATE clients SET telegram_id = :telegram_id, name = :username, username = :username, phone = :phone, " +
                    "birthday = :birthday, is_banned = :is_banned WHERE id=?",
            mapOf(
                "id" to id,
                "telegram_id" to dto.telegramId,
                "name" to dto.name,
                "username" to dto.username,
                "phone" to dto.phone,
                "birthday" to dto.birthday,
                "is_banned" to dto.isBanned,
            )
        )
    }


    override fun deleteById(id: Long) {
        jdbcTemplate.update("DELETE FROM clients WHERE id=:id", mapOf("id" to id))
    }


    override fun findByTelegramId(telegramId: String): ClientEntity? =
        jdbcTemplate.query(
            "SELECT * FROM clients WHERE telegram_id=:telegram_id",
            mapOf("telegram_id" to telegramId),
            ROW_MAPPER
        )
            .firstOrNull()


    override fun deleteByTelegramId(telegramId: String) {
        jdbcTemplate.update("DELETE FROM clients WHERE telegram_id=:telegram_id", mapOf("telegram_id" to telegramId))
    }


    override fun updateByTelegramId(telegramId: String, dto: ClientDto) {
        jdbcTemplate.update(
            "UPDATE clients SET name = :name, username = :username, phone = :phone, " +
                    "birthday = :birthday, is_banned = :is_banned WHERE telegram_id=:telegram_id",
            mapOf(
                "telegram_id" to telegramId,
                "name" to dto.name,
                "username" to dto.username,
                "phone" to dto.phone,
                "birthday" to dto.birthday,
                "is_banned" to dto.isBanned,
            )
        )
    }


    override fun findAllByisBanned(): List<ClientEntity> =
        jdbcTemplate.query("SELECT * FROM clients WHERE is_banned=:is_banned", mapOf("is_banned" to true), ROW_MAPPER)


    private companion object {
        val ROW_MAPPER = RowMapper<ClientEntity> { rs: ResultSet, _ ->
            ClientEntity(
                id = rs.getLong("id"),
                telegramId = rs.getString("telegram_id"),
                name = rs.getString("name"),
                username = rs.getString("username"),
                phone = rs.getString("phone"),
                birthday = rs.getString("birthday"),
                isBanned = rs.getBoolean("is_banned")
            )
        }
    }
}