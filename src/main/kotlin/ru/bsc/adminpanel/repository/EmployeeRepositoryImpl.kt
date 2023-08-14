package ru.bsc.adminpanel.repository

import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Repository
import ru.bsc.adminpanel.dto.EmployeeDto
import ru.bsc.adminpanel.model.EmployeeEntity
import java.sql.ResultSet

@Repository
class EmployeeRepositoryImpl(
    private val jdbcTemplate:
    NamedParameterJdbcTemplate
) : EmployeeRepository {
    override fun findAll(): List<EmployeeEntity> = jdbcTemplate.query("SELECT * FROM employees", ROW_MAPPER)

    override fun create(dto: EmployeeDto): Long {
        val passwordEncoder = BCryptPasswordEncoder()
        val keyHolder = GeneratedKeyHolder()
        val id = jdbcTemplate.update(
            "INSERT INTO employees (username, email, password)" +
                    " VALUES (:username, :email, :password)",
            MapSqlParameterSource(
                mapOf(
                    "username" to dto.username,
                    "email" to dto.email,
                    "password" to passwordEncoder.encode(dto.password),
                )
            ),
            keyHolder,
            listOf("id").toTypedArray()
        ).toLong()
        return keyHolder.key?.toLong() ?: 0
    }

    override fun findById(id: Long): EmployeeEntity? =
        jdbcTemplate.query(
            "SELECT * FROM employees WHERE id=:id", mapOf("id" to id),
            ROW_MAPPER
        ).firstOrNull()

    override fun deleteById(id: Long) {
        jdbcTemplate.update("DELETE FROM employees WHERE id=:id", mapOf("id" to id))
    }

    override fun update(id: Long, dto: EmployeeDto) {
        val passwordEncoder = BCryptPasswordEncoder()
        jdbcTemplate.update(
            "UPDATE employees SET username = :username, email = :email, password = :password WHERE id=:id",
            mapOf(
                "id" to id,
                "username" to dto.username,
                "email" to dto.email,
                "password" to passwordEncoder.encode(dto.password),
            )
        )
    }

    override fun findByUserName(username: String): EmployeeEntity? = jdbcTemplate.query(
        "SELECT * FROM employees WHERE username=:username", mapOf("username" to username),
        ROW_MAPPER
    ).firstOrNull()


    private companion object {
        val ROW_MAPPER = RowMapper<EmployeeEntity> { rs: ResultSet, _ ->
            EmployeeEntity(
                id = rs.getLong("id"),
                email = rs.getString("email"),
                username = rs.getString("username"),
                password = (rs.getString("password")),
            )
        }
    }
}