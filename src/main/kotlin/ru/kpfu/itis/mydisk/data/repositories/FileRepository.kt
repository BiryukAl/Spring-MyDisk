package ru.kpfu.itis.mydisk.data.repositories

import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import ru.kpfu.itis.mydisk.data.entity.File
import ru.kpfu.itis.mydisk.data.entity.User

interface FileRepository : JpaRepository<File, Long> {
    /*
    @Query("SELECT f FROM File f WHERE f.holderId = :idUser")
    fun findByHolderId(idUser: Long): List<File>
    */
    fun findByHolderId(idUser: User): List<File>

    fun findAllByPublicAccess(pageable: Pageable, publicAccess: Boolean = true): List<File>
}
