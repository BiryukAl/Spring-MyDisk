package ru.kpfu.itis.mydisk.presentation.controllers.rest

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import ru.kpfu.itis.mydisk.domain.services.UserService
import ru.kpfu.itis.mydisk.presentation.mapper.ToResponse
import ru.kpfu.itis.mydisk.presentation.model.UserResponse

@RestController
class UserRestController(
    private val userService: UserService,
    private val mapper: ToResponse,
) {

    @GetMapping("/api/v1/users")
    fun getAllUser(): List<UserResponse> {
        return userService.getAllUser().map(mapper::toUserResponse)
    }

}
