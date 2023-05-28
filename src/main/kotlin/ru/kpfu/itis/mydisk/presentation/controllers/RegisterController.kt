package ru.kpfu.itis.mydisk.presentation.controllers

import jakarta.validation.Valid
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
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
        @Valid
        @ModelAttribute("userForm")
        userForm: UserSignUpRequest?,
        bindingResult: BindingResult,
        modelMap: ModelMap,
    ): String {
        if (bindingResult.hasErrors()) {
//            modelMap["userForm"] = userForm
            return "sign_up"
        }
        if (userForm != null) {
            val dto = UserDto(
                name = userForm.name!!,
                email = userForm.email!!,
                password = userForm.password!!,
                avatarUrl = "https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava${(userForm.name.length % 6) + 1}.webp"
            )
            val newUser = userService.save(dto)
            if (newUser != null) {
                return "redirect:/login"
            } else {
                bindingResult.rejectValue("email", "error.user", "An account already exists for this email.")
            }
        }

//        modelMap["userForm"] = userForm
        return "sign_up"
    }
}
