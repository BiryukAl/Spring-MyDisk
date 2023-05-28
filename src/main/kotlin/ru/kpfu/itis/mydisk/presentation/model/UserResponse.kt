package ru.kpfu.itis.mydisk.presentation.model

import ru.kpfu.itis.mydisk.data.entity.CollectionFiles
import ru.kpfu.itis.mydisk.data.entity.File
import ru.kpfu.itis.mydisk.data.entity.Post
import ru.kpfu.itis.mydisk.data.entity.User

data class UserResponse(
    val id: Long,
    val name: String?,
    val email: String,
    val avatarUrl: String,
    val file: Set<File>?,
    val collectionFiles: Set<CollectionFiles>?,
    val post: Set<Post>?,
    val subscriptions: Set<User>?,
    val subscribers: Set<User>?,
)
