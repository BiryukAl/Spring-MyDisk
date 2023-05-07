package ru.kpfu.itis.mydisk.data.repositories

import org.springframework.data.jpa.repository.JpaRepository
import ru.kpfu.itis.mydisk.data.entity.User

interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): User?
}
