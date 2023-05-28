package ru.kpfu.itis.mydisk.domain.dto

import ru.kpfu.itis.mydisk.data.AuthenticationProvider

data class UserDto(
    val name: String = "User",
    val email: String,
    var password: String?,
    val authProvider: AuthenticationProvider = AuthenticationProvider.LOCAL,
    val avatarUrl: String = "https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava1.webp",
)
