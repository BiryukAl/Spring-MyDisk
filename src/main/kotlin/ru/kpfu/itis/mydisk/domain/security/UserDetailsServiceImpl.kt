package ru.kpfu.itis.mydisk.domain.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import ru.kpfu.itis.mydisk.data.repositories.UserRepository

@Service
class UserDetailsServiceImpl @Autowired constructor(
    private val userRepository: UserRepository,
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByEmail(username)
            ?: throw UsernameNotFoundException("User with email $username not found")
        return UserDetailsImpl(user)
    }
}
