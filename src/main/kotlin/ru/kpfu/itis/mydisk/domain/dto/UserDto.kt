package ru.kpfu.itis.mydisk.domain.dto

import ru.kpfu.itis.mydisk.data.entity.File
import ru.kpfu.itis.mydisk.data.entity.User
import ru.kpfu.itis.mydisk.domain.security.Role

data class UserDto(
    val id: Long,
    val name: String,
    val email: String,
    val password: String,
    val file: Set<File>,
    val subscriptions: Set<User>,
    val subscribers: Set<User>,
    val role: Role,
)
