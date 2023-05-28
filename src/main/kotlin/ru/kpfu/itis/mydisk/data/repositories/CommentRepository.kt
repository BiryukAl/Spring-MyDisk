package ru.kpfu.itis.mydisk.data.repositories

import org.springframework.data.jpa.repository.JpaRepository
import ru.kpfu.itis.mydisk.data.entity.Comment
import ru.kpfu.itis.mydisk.data.entity.Post

interface CommentRepository : JpaRepository<Comment, Long> {

    fun findByPostId(post: Post): List<Comment>
}
