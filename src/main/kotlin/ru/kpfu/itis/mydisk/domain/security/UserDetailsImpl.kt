package ru.kpfu.itis.mydisk.domain.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails
import ru.kpfu.itis.mydisk.data.entity.User

class UserDetailsImpl(
    private val user: User,
) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return AuthorityUtils.createAuthorityList(user.role.toString())
    }

    override fun getPassword(): String? = user.password

    override fun getUsername(): String = user.email
    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}
