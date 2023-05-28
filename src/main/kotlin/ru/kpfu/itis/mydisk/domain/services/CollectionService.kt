package ru.kpfu.itis.mydisk.domain.services

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import ru.kpfu.itis.mydisk.data.entity.CollectionFiles
import ru.kpfu.itis.mydisk.data.entity.User
import ru.kpfu.itis.mydisk.data.repositories.CollectionRepository

@Service
class CollectionService(
    private val collectionRepository: CollectionRepository,
) {

    fun addCollection(collectionFiles: CollectionFiles): CollectionFiles {
        return collectionRepository.save(collectionFiles)
    }

    fun getAllCollection(pageable: Pageable = Pageable.ofSize(25)): Page<CollectionFiles> {
        return collectionRepository.findAll(pageable)
    }

    fun getOneCollection(id: Long): CollectionFiles? {
        return collectionRepository.findById(id).get()
    }

    fun getCollectionOnUser(idUser: User): List<CollectionFiles> {
        return collectionRepository.getByHolderId(idUser)
    }

}
