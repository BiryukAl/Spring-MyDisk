package ru.kpfu.itis.mydisk.domain.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import ru.kpfu.itis.mydisk.data.entity.User
import ru.kpfu.itis.mydisk.data.repositories.UserRepository
import ru.kpfu.itis.mydisk.domain.dto.UserDto
import kotlin.jvm.optionals.getOrNull

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

    @OptIn(ExperimentalStdlibApi::class)
    fun getUserForId(id: Long): User? {
        return userRepository.findById(id).getOrNull()
    }


    @OptIn(ExperimentalStdlibApi::class)
    fun subscribe(myId: Long, idSubscribe: Long): User? {
        val user = userRepository.findById(myId).getOrNull()
        val sub = userRepository.findById(idSubscribe).getOrNull()
        if (user == null || sub == null) {
            return null
        }
        if (sub.subscriptions?.map { it.id }?.any { it == user.id } != true) {
            sub.subscriptions?.add(user)
            userRepository.flush()
        }
        return user
    }

    @OptIn(ExperimentalStdlibApi::class)
    fun unsubscribe(myId: Long, idSubscribe: Long): User? {
        val user = userRepository.findById(myId).getOrNull()
        val sub = userRepository.findById(idSubscribe).getOrNull()
        if (user == null || sub == null) {
            return null
        }
        if (sub.subscriptions?.map { it.id }?.any { it == user.id } == true) {
            sub.subscriptions?.remove(user)
            println(sub.subscriptions)
            userRepository.flush()
            // TODO: Работает только в дебаге .flush()
        }
        return user
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
