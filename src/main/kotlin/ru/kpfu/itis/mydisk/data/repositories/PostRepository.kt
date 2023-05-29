package ru.kpfu.itis.mydisk.data.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import ru.kpfu.itis.mydisk.data.entity.Post
import ru.kpfu.itis.mydisk.data.entity.User

interface PostRepository : JpaRepository<Post, Long> {

    fun findByHolderId(idUser: User): List<Post>


    @Query(" SELECT p FROM Post p WHERE EXISTS (SELECT 1 FROM Comment c WHERE c.postId = p AND c.holderId = :idUser) ORDER BY p.id")
    fun commentedPostOnUser(idUser: User): List<Post>

}
