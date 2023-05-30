package ru.kpfu.itis.mydisk.presentation.controllers

import jakarta.servlet.http.HttpServletRequest
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatusCode
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder
import ru.kpfu.itis.mydisk.data.entity.File
import ru.kpfu.itis.mydisk.data.repositories.UserRepository
import ru.kpfu.itis.mydisk.data.storage.FileSystemStorageService
import ru.kpfu.itis.mydisk.data.storage.RandomFilePath
import ru.kpfu.itis.mydisk.domain.services.FilesService
import java.io.IOException
import java.security.Principal


@Controller
class FilesController(
    private val filesService: FilesService,
    private val fileSystemStorageService: FileSystemStorageService,
    private val userRepository: UserRepository,
) {

    @GetMapping("/files")
    fun getPublicFilesPage(
        modelMap: ModelMap,
    ): String {
        modelMap["files"] = filesService.getPublicFile()
        return "public_files"
    }

    @GetMapping("/user/add/file")
    fun getUploadPage(
        modelMap: ModelMap,
    ): String {
        return "upload_file"
    }

    @PostMapping("/user/add/file")
    fun upload(
        @RequestParam("file")
        file: MultipartFile,
        @RequestParam("title")
        title: String?,
        @RequestParam("description")
        description: String?,
        @RequestParam("access")
        access: String?,
        principal: Principal,
    ): String {
        if (title == null) {
            return "upload_file"
        }
        val publicAccess = access == "public"

        val newNameFile = RandomFilePath.generateFileName(
            file.originalFilename.toString()
        )

        val user = userRepository.findByEmail(principal.name)!!

        filesService.save(
            File(
                title = title,
                description = description,
                holderId = user,
                nameFile = newNameFile,
                publicAccess = publicAccess
            )
        )
        fileSystemStorageService.store(file, newNameFile)
        return "redirect:" + MvcUriComponentsBuilder
            .fromMappingName("UC#getProfilePage").build()

    }


    @GetMapping("/file/{id:\\d+}")
    @ResponseBody
    fun downloadFile(
        @PathVariable("id")
        id: String,
        request: HttpServletRequest,
    ): ResponseEntity<Resource> {
        val idFile = id.toLong()

        val file = filesService.getById(idFile) ?: return ResponseEntity(HttpStatusCode.valueOf(404))

        val resource = fileSystemStorageService.loadAsResource(file.nameFile)

        var contentType: String? = null
        try {
            contentType = request.servletContext.getMimeType(resource.file.absolutePath)
        } catch (_: IOException) {
        }

        if (contentType == null) {
            contentType = "application/octet-stream"
        }

        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(contentType))
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.title + "\"")
            .body(resource)

    }

}
