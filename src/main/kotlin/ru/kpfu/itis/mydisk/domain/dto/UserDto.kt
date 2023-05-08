package ru.kpfu.itis.mydisk.domain.dto

data class UserDto(
    val name: String,
    val email: String,
    var password: String,
)