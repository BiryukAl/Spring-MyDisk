package ru.kpfu.itis.mydisk.data.repositories

import org.springframework.data.jpa.repository.JpaRepository
import ru.kpfu.itis.mydisk.data.entity.Post
import ru.kpfu.itis.mydisk.data.entity.User

interface PostRepository : JpaRepository<Post, Long> {

    fun findByHolderId(idUser: User): List<Post>

}
