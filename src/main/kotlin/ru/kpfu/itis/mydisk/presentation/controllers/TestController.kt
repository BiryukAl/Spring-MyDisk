package ru.kpfu.itis.mydisk.presentation.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class TestController {

    @GetMapping("/test")
    fun getTestTemplate(
    ): String {
        return "index"
    }
}
