package ru.kpfu.itis.mydisk.presentation.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import ru.kpfu.itis.mydisk.domain.services.UserService
import ru.kpfu.itis.mydisk.presentation.mapper.ToResponse
import ru.kpfu.itis.mydisk.presentation.model.UserResponse

@RestController
class RestTestController(
    private val userService: UserService,
    private val mapper: ToResponse,
) {

    // TODO: Организация REST API хотя бы для одной сущности.
    //  Подключение генерации openAPI.
    //  А также написание тестов через http-файлы Idea или через Postman.
    @GetMapping("/api/v1/users")
    fun getAllUser(): List<UserResponse> {
        return userService.getAllUser().map(mapper::toUserResponse)
    }


}
