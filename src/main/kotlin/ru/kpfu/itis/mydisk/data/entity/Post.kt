package ru.kpfu.itis.mydisk.data.entity

data class Post(
    val title: String,
    val description: String,
    val holder: Long,
    val img: String? = null,
    val id: Long? = null,
)
