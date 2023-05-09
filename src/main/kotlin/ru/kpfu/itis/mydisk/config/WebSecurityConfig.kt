package ru.kpfu.itis.mydisk.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import ru.kpfu.itis.mydisk.domain.security.OAuth2LoginSuccessHandler

@Configuration
@EnableWebSecurity
class WebSecurityConfig @Autowired constructor(
    private val oAuth2LoginSuccessHandler: OAuth2LoginSuccessHandler,
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http.csrf().disable()
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
                it.loginPage("/login")
                    .loginProcessingUrl("/login")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/user")
                    .failureUrl("/login?error")
            }
            .logout {
                it.invalidateHttpSession(false)
                    .clearAuthentication(true)
                    .logoutRequestMatcher(AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login?logout")
                    .permitAll()

            }
            .oauth2Login {
                it.loginPage("/login")
                it.successHandler(oAuth2LoginSuccessHandler)
                it.defaultSuccessUrl("/user")
            }
            .build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}
