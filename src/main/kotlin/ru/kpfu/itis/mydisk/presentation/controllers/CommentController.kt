package ru.kpfu.itis.mydisk.presentation.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder
import ru.kpfu.itis.mydisk.data.entity.Comment
import ru.kpfu.itis.mydisk.domain.services.CommentService
import ru.kpfu.itis.mydisk.domain.services.PostService
import ru.kpfu.itis.mydisk.domain.services.UserService
import java.security.Principal

@Controller
class CommentController(
    private val commentService: CommentService,
    private val postService: PostService,
    private val userService: UserService,
) {

    @PostMapping("/user/add/comment/post/{id:\\d+}")
    fun save(
        @PathVariable("id") id: String,
        @RequestParam("body") body: String?,
        principal: Principal?,
    ): String {
        val idPost = id.toLong()
        val post = postService.getOnePost(idPost)
        if (body.isNullOrBlank() || principal == null || post == null) {
            return "redirect:" + MvcUriComponentsBuilder.fromMappingName("PC#getAllPost").build()
        }
        val user = userService.getUserForEmail(principal.name)!!
        commentService.save(Comment(body, user, post))
        return "redirect:" + MvcUriComponentsBuilder.fromMappingName("PC#getAllPost").build()
    }
}
