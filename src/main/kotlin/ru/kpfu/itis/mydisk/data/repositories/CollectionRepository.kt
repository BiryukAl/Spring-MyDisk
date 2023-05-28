package ru.kpfu.itis.mydisk.data.repositories

import org.springframework.data.jpa.repository.JpaRepository
import ru.kpfu.itis.mydisk.data.entity.CollectionFiles
import ru.kpfu.itis.mydisk.data.entity.User

interface CollectionRepository : JpaRepository<CollectionFiles, Long> {
    fun getByHolderId(idUser: User): List<CollectionFiles>
}
