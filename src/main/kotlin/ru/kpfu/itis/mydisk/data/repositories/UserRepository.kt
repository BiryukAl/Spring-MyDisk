package ru.kpfu.itis.mydisk.data.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import ru.kpfu.itis.mydisk.data.AuthenticationProvider
import ru.kpfu.itis.mydisk.data.entity.User

interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): User?

    @Modifying
    @Query("update User u set  u.authProvider = ?1 where u.id = ?2")
    fun updateUserByAuthProvider(
        authenticationProvider: AuthenticationProvider,
        name: String,
        avatarUrl: String?,
        userID: Long,
    )
}
