package ru.kpfu.itis.mydisk.domain.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import ru.kpfu.itis.mydisk.data.entity.User
import ru.kpfu.itis.mydisk.data.repositories.UserRepository
import ru.kpfu.itis.mydisk.domain.dto.UserDto

@Service
class UserService @Autowired constructor(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
) {
    fun getAllUser(): List<User> {
        return userRepository.findAll()
    }

    fun getUserForEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }

    fun getUserForId(id: Long): User? {
        return userRepository.findById(id).get()
    }

    fun save(user: UserDto): User? {
        user.password = passwordEncoder.encode(user.password)
        if (userRepository.findByEmail(user.email) != null) {
            return null
        }
        with(user) {
            return userRepository.save(
                User(
                    name = name,
                    email = email,
                    password = password!!,
                    authProvider = user.authProvider,
                    avatarUrl = user.avatarUrl
                )
            )
        }
    }
}
