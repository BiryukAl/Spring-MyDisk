package ru.kpfu.itis.mydisk.domain.services

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import ru.kpfu.itis.mydisk.data.entity.Post
import ru.kpfu.itis.mydisk.data.entity.User
import ru.kpfu.itis.mydisk.data.repositories.PostRepository

@Service
class PostService(
    private val postRepository: PostRepository,
) {

    fun getAllPost(pageable: Pageable = Pageable.ofSize(25)): Page<Post> {
        return postRepository.findAll(pageable)
    }

    fun addPost(post: Post): Post {
        return postRepository.save(post)
    }

    fun getOnePost(id: Long): Post? {
        return postRepository.findById(id).get()
    }

    fun getPostOnUser(idUser: User): List<Post> {
        return postRepository.findByHolderId(idUser)
    }
}