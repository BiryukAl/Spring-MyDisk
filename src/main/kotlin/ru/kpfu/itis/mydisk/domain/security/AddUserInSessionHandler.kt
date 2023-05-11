package ru.kpfu.itis.mydisk.domain.security

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.DefaultRedirectStrategy
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component
import ru.kpfu.itis.mydisk.domain.services.UserService

@Component
class AddUserInSessionHandler @Autowired constructor(
    private val userService: UserService,
) : AuthenticationSuccessHandler {
    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication,
    ) {
        val email = (authentication.principal as UserDetailsImpl).username
        val user = userService.getUserForEmail(email) ?: throw UsernameNotFoundException("User with email not found")

        request.session.setAttribute("username", user.name)
        val redirectStrategy = DefaultRedirectStrategy()
        redirectStrategy.sendRedirect(request, response, "/user")
    }


}