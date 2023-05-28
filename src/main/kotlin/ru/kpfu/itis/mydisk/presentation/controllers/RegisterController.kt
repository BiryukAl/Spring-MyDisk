package ru.kpfu.itis.mydisk.presentation.controllers

import jakarta.validation.Valid
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder
import ru.kpfu.itis.mydisk.domain.dto.UserDto
import ru.kpfu.itis.mydisk.domain.services.UserService
import ru.kpfu.itis.mydisk.presentation.model.UserSignUpRequest

@Controller
class RegisterController(
    private val userService: UserService,
) {

    @GetMapping("/login")
    fun getLoginPage(
        modelMap: ModelMap,
    ): String {
        return "log_in"
    }

    @GetMapping("/signup")
    fun getSignupPage(
        modelMap: ModelMap,
        userForm: UserSignUpRequest,
    ): String {
        modelMap["userForm"] = userForm
        return "sign_up"
    }


    @PostMapping("/signup")
    fun signup(
        @ModelAttribute
        @Valid
        userForm: UserSignUpRequest?,
        modelMap: ModelMap,
        result: BindingResult,
    ): String {
        if (result.hasErrors()) {
            return "sign_up"
        }
        if (userForm != null) {
            userService.save(
                UserDto(
                    name = userForm.name!!,
                    email = userForm.email!!,
                    password = userForm.password!!,
                    avatarUrl = "https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava${(userForm.name.length % 6) + 1}.webp"
                )
            )

            return "redirect:" + MvcUriComponentsBuilder.fromMappingName("RC#getLoginPage").build()
        }
        return "sign_up"
    }
}
