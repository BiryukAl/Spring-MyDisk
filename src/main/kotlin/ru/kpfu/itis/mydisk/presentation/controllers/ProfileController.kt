package ru.kpfu.itis.mydisk.presentation.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping
import ru.kpfu.itis.mydisk.domain.services.CollectionService
import ru.kpfu.itis.mydisk.domain.services.FilesService
import ru.kpfu.itis.mydisk.domain.services.PostService
import ru.kpfu.itis.mydisk.domain.services.UserService
import ru.kpfu.itis.mydisk.presentation.mapper.ToResponse
import java.security.Principal

@Controller
class ProfileController(
    private val userService: UserService,
    private val mapper: ToResponse,
    private val collectionService: CollectionService,
    private val postService: PostService,
    private val filesService: FilesService,
) {

    @GetMapping("/user")
    fun getProfilePage(
        modelMap: ModelMap,
        principal: Principal,
    ): String {
        val user = userService.getUserForEmail(principal.name) ?: return "404"

        modelMap["user_file"] = filesService.getFilesOnUser(user)
        modelMap["user_collection"] = collectionService.getCollectionOnUser(user)
        modelMap["user_post"] = postService.getPostOnUser(user)
        modelMap["userProfile"] = mapper.toUserResponse(user)
        modelMap["authProvider"] = user.authProvider
        return "my_profile"
    }

}
