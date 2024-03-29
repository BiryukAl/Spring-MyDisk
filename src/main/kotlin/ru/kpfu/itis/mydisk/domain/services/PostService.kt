package ru.kpfu.itis.mydisk.domain.services

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import ru.kpfu.itis.mydisk.data.entity.Post
import ru.kpfu.itis.mydisk.data.entity.User
import ru.kpfu.itis.mydisk.data.repositories.PostRepository
import ru.kpfu.itis.mydisk.data.repositories.TaskCriteriaDao
import kotlin.jvm.optionals.getOrNull

@Service
class PostService(
    private val postRepository: PostRepository,
    private val taskCriteriaDao: TaskCriteriaDao,
) {

    fun getAllPost(pageable: Pageable = Pageable.ofSize(25)): Page<Post> {
        return postRepository.findAll(pageable)
    }

    fun addPost(post: Post): Post {
        return postRepository.save(post)
    }

    @OptIn(ExperimentalStdlibApi::class)
    fun getOnePost(id: Long): Post? {
        return postRepository.findById(id).getOrNull()
    }

    fun getPostOnUser(idUser: User): List<Post> {
        return postRepository.findByHolderId(idUser)
    }

    fun commentedPostOnUser(user: User): List<Post> {
        return postRepository.commentedPostOnUser(user)
    }

    fun findOnPost(query: String): List<Post> {
        return taskCriteriaDao.findOnPost(query)
    }

}
