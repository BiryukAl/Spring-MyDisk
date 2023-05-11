package ru.kpfu.itis.mydisk.domain.security

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class ProxyPasswordEncoder : PasswordEncoder {

    private val internalPasswordEncoder: PasswordEncoder = BCryptPasswordEncoder();
    override fun encode(rawPassword: CharSequence?): String {
        return internalPasswordEncoder.encode(rawPassword)
    }

    override fun matches(rawPassword: CharSequence?, encodedPassword: String?): Boolean {
        return internalPasswordEncoder.matches(rawPassword, encodedPassword)
    }
}
