package ru.kpfu.itis.mydisk.data.entity

// TODO: add entity
data class Collection(
    val title: String,
    val description: String,
    val files: Set<File>,
    val holder: Long,
    val id: Long? = null,
)