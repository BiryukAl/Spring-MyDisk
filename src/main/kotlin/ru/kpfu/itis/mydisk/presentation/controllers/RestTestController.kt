package ru.kpfu.itis.mydisk.presentation.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import ru.kpfu.itis.mydisk.domain.services.UserService
import ru.kpfu.itis.mydisk.presentation.mapper.ToResponse
import ru.kpfu.itis.mydisk.presentation.model.UserResponse

@RestController
class RestTestController @Autowired constructor(
    private val userService: UserService,
    private val mapper: ToResponse,
) {
    @GetMapping("/users")
    fun getAllUser(): List<UserResponse> {
        return userService.getAllUser().map(mapper::toUserResponse)
    }


}
