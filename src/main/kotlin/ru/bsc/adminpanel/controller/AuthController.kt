package ru.bsc.adminpanel.controller

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.slf4j.LoggerFactory
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.bsc.adminpanel.dto.EmployeeDto
import ru.bsc.adminpanel.dto.LoginDto
import ru.bsc.adminpanel.service.EmployeeService
import java.util.*

@RestController
@RequestMapping("/")
class AuthController(private val employeeService: EmployeeService) {
    val logger = LoggerFactory.getLogger(AuthController::class.java)

    @PostMapping("register")
    fun register(@RequestBody dto: EmployeeDto): ResponseEntity<Long> {
        return ResponseEntity.ok(employeeService.create(dto))
    }

    @PostMapping("login")
    fun login(@RequestBody loginDto: LoginDto, response: HttpServletResponse): ResponseEntity<Any> {
        val employee: EmployeeDto
        try {
            employee = employeeService.findByUserName(loginDto.username)
        } catch (e: RuntimeException) {
            return ResponseEntity.badRequest().body("Employee not found")
        }

        if (!employee.comparePassword(loginDto.password)) {
            return ResponseEntity.badRequest().body("invalid password")
        }

        val issure = employee.id.toString()
        val jwt = Jwts.builder()
            .setIssuer(issure)
            .setExpiration(Date(System.currentTimeMillis() + 60 * 24 * 1000)) // 1 day
            .signWith(SignatureAlgorithm.HS256, "secret").compact() // спрятать

        val cookie = Cookie("jwt", jwt)
        cookie.isHttpOnly = true

        response.addCookie(cookie)

        return ResponseEntity.ok("Success")
    }


    @GetMapping("employee")
    fun employee(@CookieValue("jwt") jwt: String?): ResponseEntity<Any> {
        try {
            if (jwt == null) {
                return ResponseEntity.status(401).body("unauthenticated")
            }
            val body = Jwts.parser().setSigningKey("secret").parseClaimsJws(jwt).body
            return ResponseEntity.ok(employeeService.getById(id = body.issuer.toLong()))
        } catch (e: Exception) {
            return ResponseEntity.status(401).body("unauthenticated, $e")
        }
    }

    @PostMapping("logout")
    fun logout(response: HttpServletResponse): ResponseEntity<Any> {
        val cookie = Cookie("jwt", "")
        cookie.maxAge = 0
        response.addCookie(cookie)

        return ResponseEntity.ok("Success")
    }
}