package ru.kpfu.itis.mydisk.data.entity

// TODO: add entity
data class Comment(
    val body: String,
    val holderId: Long,
    val post: Post,
    val id: Long? = null,
)
