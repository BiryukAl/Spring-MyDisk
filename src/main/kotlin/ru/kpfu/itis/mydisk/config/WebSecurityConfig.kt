package ru.kpfu.itis.mydisk.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import ru.kpfu.itis.mydisk.domain.security.AddUserInSessionHandler
import ru.kpfu.itis.mydisk.domain.security.OAuth2LoginSuccessHandler

@Configuration
@EnableWebSecurity
class WebSecurityConfig @Autowired constructor(
    private val oAuth2LoginSuccessHandler: OAuth2LoginSuccessHandler,
    private val addUserInSessionHandler: AddUserInSessionHandler,
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf {
                it
                    .disable()
            }
            .authorizeHttpRequests {
                it
                    .requestMatchers("/login", "/signup").anonymous()
                    .requestMatchers("/user", "/logout").authenticated()
                    .requestMatchers("/oauth2/**").permitAll()
                    .requestMatchers("/favicon.ico").permitAll()
                    .anyRequest().permitAll()
                    .and()

            }
            .formLogin {
                it
                    .loginPage("/login")
                    .loginProcessingUrl("/login")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .successHandler(addUserInSessionHandler)
                    .failureUrl("/login?error")
            }
            .logout {
                it
                    .invalidateHttpSession(false)
                    .deleteCookies("username")
                    .clearAuthentication(true)
                    .logoutRequestMatcher(AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login?logout")
                    .permitAll()

            }
            .oauth2Login {
                it
                    .loginPage("/login")
                    .successHandler(oAuth2LoginSuccessHandler)
            }

        return http.build()
    }
}
