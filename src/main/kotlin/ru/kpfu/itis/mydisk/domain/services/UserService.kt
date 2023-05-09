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

    fun save(user: UserDto): User? {
        user.password = passwordEncoder.encode(user.password)
        with(user) {
            return userRepository.save(User(name, email, password!!, user.authProvider))
        }
    }
}
