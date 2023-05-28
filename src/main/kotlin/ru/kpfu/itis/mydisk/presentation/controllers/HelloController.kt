package ru.kpfu.itis.mydisk.presentation.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder

@Controller
class HelloController {

    @GetMapping("/")
    fun mainPage(): String = "redirect:" + MvcUriComponentsBuilder.fromMappingName("PC#getAllPost").build()
}
