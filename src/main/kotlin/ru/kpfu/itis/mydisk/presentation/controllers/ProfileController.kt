package ru.kpfu.itis.mydisk.presentation.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping
import ru.kpfu.itis.mydisk.domain.services.UserService
import ru.kpfu.itis.mydisk.presentation.mapper.ToResponse
import java.security.Principal

@Controller
class ProfileController @Autowired constructor(
    private val userService: UserService,
    private val mapper: ToResponse,
) {

    @GetMapping("/user")
    fun getProfilePage(
        modelMap: ModelMap,
        principal: Principal,
    ): String {
        val user = userService.getUserForEmail(principal.name) ?: return "404"
        modelMap["userProfile"] = mapper.toUserResponse(user)
        modelMap["authProvider"] = user.authProvider
        return "profile"
    }

}
