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

    // TODO: Наличие 2 нестандартных методов для репозиториев Spring:
    //  самостоятельно написанных методов  с использованием @Query, CriteriaBuilder,
    //  но не повторяющих функционал методов, описываемых в Spring Data JPA.

    // TODO: Один запрос с подзапросом.

    fun findByHolderId(idUser: User): List<File>

    fun findAllByPublicAccess(pageable: Pageable, publicAccess: Boolean = true): List<File>
}
