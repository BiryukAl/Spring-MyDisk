package ru.kpfu.itis.mydisk.presentation.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder
import ru.kpfu.itis.mydisk.data.entity.Post
import ru.kpfu.itis.mydisk.domain.services.CommentService
import ru.kpfu.itis.mydisk.domain.services.PostService
import ru.kpfu.itis.mydisk.domain.services.UserService
import java.security.Principal

@Controller
class PostController(
    private val postService: PostService,
    private val userService: UserService,
    private val commentService: CommentService,
) {
    @GetMapping("/posts")
    fun getAllPost(modelMap: ModelMap): String {
        modelMap["posts"] = postService.getAllPost().toList()
        return "posts_page"
    }

    @GetMapping("/user/add/post")
    fun getAddPostPage(
    ): String = "add_post"

    @PostMapping("/user/add/post")
    fun addPost(
        modelMap: ModelMap,
        @RequestParam("title")
        title: String?,
        @RequestParam("description")
        description: String?,
        @RequestParam("img")
        imgLink: String?,
        principal: Principal,
    ): String {
        if (title == null) {
            return "add_post"
        }
        val user = userService.getUserForEmail(principal.name)
        postService.addPost(
            Post(
                title = title,
                description = description,
                img = imgLink,
                holderId = user
            )
        )
        return "redirect:" + MvcUriComponentsBuilder
            .fromMappingName("PC#getProfilePage")
            .build()

    }

    @GetMapping("/post/{id:\\d+}")
    fun getOnePost(
        @PathVariable("id")
        idPost: String,
        modelMap: ModelMap,
    ): String {
        val id = idPost.toLong()
        val post = postService.getOnePost(id) ?: return "404"
        modelMap["post"] = post
        val comments = commentService.getCommentsONPost(post)
        modelMap["comments"] = comments
        return "one_post"
    }
}
