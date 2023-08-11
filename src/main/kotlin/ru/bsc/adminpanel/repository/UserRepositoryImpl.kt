package ru.bsc.adminpanel.repository

import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Repository
import ru.bsc.adminpanel.dto.UserDto
import ru.bsc.adminpanel.model.UserEntity
import java.sql.ResultSet

@Repository
class UserRepositoryImpl(
    private val jdbcTemplate:
    NamedParameterJdbcTemplate
) : UserRepository {
    override fun findAll(): List<UserEntity> = jdbcTemplate.query("SELECT * FROM users", ROW_MAPPER)

    override fun create(dto: UserDto): Long {
        val keyHolder = GeneratedKeyHolder()
        val id = jdbcTemplate.update(
            "INSERT INTO users (name, email, password)" +
                    " VALUES (:name, :email, :password)",
            MapSqlParameterSource(
                mapOf(
                    "name" to dto.name,
                    "email" to dto.email,
                    "password" to dto.password,
                )
            ),
            keyHolder,
            listOf("id").toTypedArray()
        ).toLong()
        return keyHolder.keys?.getValue("id") as Long
    }

    override fun findById(id: Long): UserEntity? =
        jdbcTemplate.query(
            "SELECT * FROM users WHERE id=?", mapOf("id" to id),
            ROW_MAPPER
        ).firstOrNull()

    override fun deleteById(id: Long) {
        jdbcTemplate.update("DELETE FROM users WHERE id=?", mapOf("id" to id))
    }

    override fun update(id: Long, dto: UserDto) {
        jdbcTemplate.update(
            "UPDATE users SET name = :name, email = :email, password = :password WHERE id=?",
                mapOf(
                    "id" to id,
                    "name" to dto.name,
                    "email" to dto.email,
                    "password" to dto.password,
                )
        )
    }

    override fun findByName(name: String): UserEntity? = jdbcTemplate.query(
        "SELECT * FROM users WHERE name=?", mapOf("name" to name),
        ROW_MAPPER
    ).firstOrNull()


    private companion object {
        val ROW_MAPPER = RowMapper<UserEntity> { rs: ResultSet, _ ->
            val passwordEncoder = BCryptPasswordEncoder()
            UserEntity(
                id = rs.getLong("id"),
                email = rs.getString("email"),
                name = rs.getString("name"),
                password = passwordEncoder.encode(rs.getString("password")),
            )
        }
    }
}