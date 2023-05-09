package ru.kpfu.itis.mydisk.domain.security

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.stereotype.Component
import ru.kpfu.itis.mydisk.data.AuthenticationProvider
import ru.kpfu.itis.mydisk.data.entity.User
import ru.kpfu.itis.mydisk.data.repositories.UserRepository

@Component
class OAuth2LoginSuccessHandler @Autowired constructor(
    /*private val userService: UserService,*/
    private val userRepository: UserRepository,
) : SimpleUrlAuthenticationSuccessHandler() {

    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication,
    ) {
        val oAuth2User = authentication.principal as OAuth2User
        val idGit = oAuth2User.name
        val emailAny = oAuth2User.attributes["email"] ?: oAuth2User.attributes["login"]
        val name = oAuth2User.attributes["login"] as String
        val email = emailAny as String
        val avatarUrl = oAuth2User.attributes["avatar_url"] as String?

        val userLocal = userRepository.findByEmail(email)

        if (userLocal == null) {
            println("OAuth2: New User")
            userRepository.save(User(name, idGit, null, AuthenticationProvider.GITHUB, avatarUrl = avatarUrl))
        } else {
            println("OAuth2: Existing User")
            userRepository.updateUserByAuthProvider(
                AuthenticationProvider.GITHUB,
                name,
                avatarUrl ?: userLocal.avatarUrl!!,
                userLocal.id!!
            )
        }
    }
}
