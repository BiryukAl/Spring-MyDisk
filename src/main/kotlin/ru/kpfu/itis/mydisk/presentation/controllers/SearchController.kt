package ru.kpfu.itis.mydisk.presentation.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder
import ru.kpfu.itis.mydisk.domain.services.PostService

@Controller
class SearchController(
    private val postService: PostService,
) {

    @PostMapping("/search/post")
    fun searchPost(
        @RequestParam("query")
        query: String?,
        modelMap: ModelMap,
    ): String {
        if (query.isNullOrBlank()) {
            return "redirect:" + MvcUriComponentsBuilder.fromMappingName("PC#getAllPost").build()
        }
        modelMap["posts"] = postService.findOnPost(query)
        return "posts_page"
    }
}