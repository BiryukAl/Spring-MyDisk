package ru.kpfu.itis.mydisk.presentation.controllers

import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
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
class UserController @Autowired constructor(
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

        userForm?.let {
            val newUser = userService.save(UserDto(userForm.name!!, userForm.email!!, userForm.password!!))
            newUser?.let { id ->
                return "redirect:" +
                        MvcUriComponentsBuilder.fromMappingName("TC#getTestTemplate").build()
            }
            return "redirect:" + MvcUriComponentsBuilder.fromMappingName("TC#getTestTemplate").build()
        }
        return "sign_up"
    }
}
