package ru.kpfu.itis.mydisk.presentation.mapper

import org.springframework.stereotype.Component
import ru.kpfu.itis.mydisk.data.entity.User
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
                file = file,
                collectionFiles = collectionFiles,
                post = post,
                subscriptions = subscriptions!!,
                subscribers = subscribers!!,
            )
        }
    }

}
