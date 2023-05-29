package ru.kpfu.itis.mydisk.presentation.model

import ru.kpfu.itis.mydisk.data.entity.User

data class UserResponse(
    val id: Long?,
    val name: String?,
    val email: String?,
    val avatarUrl: String?,
    val subscriptions: Set<User>?,
    val subscribers: Set<User>?,
)
