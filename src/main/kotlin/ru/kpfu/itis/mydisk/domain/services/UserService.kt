package ru.kpfu.itis.mydisk.domain.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.kpfu.itis.mydisk.data.entity.User
import ru.kpfu.itis.mydisk.data.repositories.UserRepository

@Service
class UserService @Autowired constructor(
    private val userRepository: UserRepository,
) {
    fun getAllUser(): List<User> {
        return userRepository.findAll()
    }


}
