package ru.kpfu.itis.mydisk.presentation.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder
import ru.kpfu.itis.mydisk.data.entity.CollectionFiles
import ru.kpfu.itis.mydisk.domain.services.CollectionService
import ru.kpfu.itis.mydisk.domain.services.FilesService
import ru.kpfu.itis.mydisk.domain.services.UserService
import java.security.Principal

@Controller
class CollectionController(
    private val collectionService: CollectionService,
    private val filesService: FilesService,
    private val userService: UserService,
) {

    @GetMapping("/collections")
    fun getCollectionPage(
        modelMap: ModelMap,
    ): String {
        modelMap["collections"] = collectionService.getAllCollection()
        return "collections_page"
    }

    @GetMapping("/collection/{id:\\d+}")
    fun getOneCollection(
        @PathVariable("id")
        idParameter: String,
        modelMap: ModelMap,
//        principal: Principal?
    ): String {
        val idCollection = idParameter.toLong()

        val collection = collectionService.getOneCollection(idCollection) ?: return "404"

        // TODO: add collection delete button visible
        /*val user = userService.getUserForEmail(principal.name)*/
        modelMap["collection_is_my"] = /*user?.id!! == collection.holderId.id*/ false

        modelMap["collection"] = collection
        return "one_collection"


    }

    @GetMapping("/user/add/collection")
    fun getAddCollectionPage(
        modelMap: ModelMap,
        principal: Principal,
    ): String {
        val email = principal.name
        val idUser = userService.getUserForEmail(email)!!
        val files = filesService.getFilesOnUser(idUser)
        modelMap["user_files"] = files
        return "add_collection"
    }

    @PostMapping("/user/add/collection")
    fun composeCollection(
        @RequestParam("title")
        title: String?,
        @RequestParam("chose_files")
        choiceFile: String?,
        principal: Principal,
    ): String {

        if (title == null || choiceFile == null) {
            return "add_collection"
        }
        val idFiles = choiceFile.split(",")
        if (idFiles.size == 1) {
            return "add_collection"
        }
        val user = userService.getUserForEmail(principal.name)

        val files = idFiles
            .map { it.toLong() }
            .map { filesService.getById(it)!! }
            .toSet()

        val description: String = files.joinToString(",") { it.title }
        collectionService.addCollection(CollectionFiles(title, description, files, user!!))
        return "redirect:" + MvcUriComponentsBuilder
            .fromMappingName("UC#getProfilePage").build()
    }
}
