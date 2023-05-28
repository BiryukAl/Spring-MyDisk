package ru.kpfu.itis.mydisk.domain.services

import org.springframework.stereotype.Service
import ru.kpfu.itis.mydisk.data.entity.Comment
import ru.kpfu.itis.mydisk.data.repositories.CommentRepository

@Service
class CommentService(
    private val commentRepository: CommentRepository,
) {

    fun save(comment: Comment): Comment {
        return commentRepository.save(comment)
    }

}
