package ru.bsc.adminpanel.repository

interface BaseRepository<T, D> {
    fun findAll(): List<T>
    fun create(dto: D): Long
    fun findById(id: Long): T?
    fun update(id: Long, dto: D)
    fun deleteById(id: Long)
}