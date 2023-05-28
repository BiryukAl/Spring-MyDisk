package ru.kpfu.itis.mydisk.domain.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import ru.kpfu.itis.mydisk.data.entity.File
import ru.kpfu.itis.mydisk.data.entity.User
import ru.kpfu.itis.mydisk.data.repositories.FileRepository

@Service
class FilesService @Autowired constructor(
    private val fileRepository: FileRepository,
) {

    fun getAllFiles(): List<File> {
        return fileRepository.findAll()
    }

    fun getById(id: Long): File? {
        return fileRepository.findById(id).get()
    }

    fun getFilesOnUser(idUser: User): List<File> {
        return fileRepository.findByHolderId(idUser)
    }

    fun getPublicFile(pageable: Pageable = Pageable.ofSize(25)): List<File> {
        return fileRepository.findAllByPublicAccess(pageable)
    }

    fun save(file: File): File {
        return fileRepository.save(file)
    }


}
