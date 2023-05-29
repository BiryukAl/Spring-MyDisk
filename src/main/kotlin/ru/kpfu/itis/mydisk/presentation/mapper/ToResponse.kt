package ru.kpfu.itis.mydisk.presentation.mapper

import org.springframework.stereotype.Component
import ru.kpfu.itis.mydisk.data.entity.Post
import ru.kpfu.itis.mydisk.data.entity.User
import ru.kpfu.itis.mydisk.presentation.model.PostResponse
import ru.kpfu.itis.mydisk.presentation.model.UserResponse

@Component
class ToResponse {

    fun toUserResponse(user: User): UserResponse {
        with(user) {
            return UserResponse(
                id = id!!,
                name = name,
                email = email,
                avatarUrl = avatarUrl ?: "",
                subscriptions = subscriptions!!,
                subscribers = subscribers!!,
            )
        }
    }

    fun toPostResponse(post: Post): PostResponse {
        with(post) {
            return PostResponse(
                id = id,
                description = description,
                title = title,
                img = img,
                holderId = holderId?.let { toUserResponse(holderId) },
            )
        }
    }

}
