package ru.kpfu.itis.mydisk.presentation.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import ru.kpfu.itis.mydisk.data.entity.User
import ru.kpfu.itis.mydisk.domain.services.CollectionService
import ru.kpfu.itis.mydisk.domain.services.FilesService
import ru.kpfu.itis.mydisk.domain.services.PostService
import ru.kpfu.itis.mydisk.domain.services.UserService
import ru.kpfu.itis.mydisk.presentation.mapper.ToResponse
import java.security.Principal

@Controller
class UserController(
    private val userService: UserService,
    private val mapper: ToResponse,
    private val filesService: FilesService,
    private val collectionService: CollectionService,
    private val postService: PostService,
) {

    @GetMapping("/users")
    fun getAllUserPage(
        modelMap: ModelMap,
    ): String {
        modelMap["users"] = userService.getAllUser()
        return "all_user"
    }

    @GetMapping("/user/{id:\\d+}")
    fun getOneUser(
        modelMap: ModelMap,
        @PathVariable("id")
        idUser: String,
    ): String {
        val id = idUser.toLong()

        val user = userService.getUserForId(id) ?: return "404"

        initUser(modelMap, user)

        return "user_profile"

    }

    @GetMapping("/user")
    fun getProfilePage(
        modelMap: ModelMap,
        principal: Principal,
    ): String {
        val user = userService.getUserForEmail(principal.name) ?: return "404"

        initUser(modelMap, user)

        modelMap["comment_in_post"] = postService.commentedPostOnUser(user)
        return "my_profile"
    }

    fun initUser(modelMap: ModelMap, user: User) {
        modelMap["user_file"] = filesService.getFilesOnUser(user)
        modelMap["user_collection"] = collectionService.getCollectionOnUser(user)
        modelMap["user_post"] = postService.getPostOnUser(user)
        modelMap["userProfile"] = mapper.toUserResponse(user)
        modelMap["authProvider"] = user.authProvider
    }
}
