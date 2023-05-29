package ru.kpfu.itis.mydisk.presentation.model

data class PostResponse(
    val id: Long?,
    val description: String?,
    val title: String?,
    val img: String?,
    val holderId: UserResponse?,
)
