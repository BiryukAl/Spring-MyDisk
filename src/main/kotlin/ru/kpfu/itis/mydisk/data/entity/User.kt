package ru.kpfu.itis.mydisk.data.entity

import jakarta.persistence.*
import ru.kpfu.itis.mydisk.data.AuthenticationProvider
import ru.kpfu.itis.mydisk.domain.security.Role

@Entity
@Table(name = "usr")
data class User(

    @Column(name = "name")
    val name: String,
    @Column(name = "email", unique = true)
    val email: String,
    @Column(name = "password")
    val password: String?,

    @Column
    @Enumerated(value = EnumType.STRING)
    val authProvider: AuthenticationProvider,

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    val role: Role = Role.ROLE_USER,

    @Column
    val avatarUrl: String? = null,


    @ManyToMany()
    val file: Set<File>? = null,

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "subscriptions",
        joinColumns = [JoinColumn(name = "userId")],
        inverseJoinColumns = [JoinColumn(name = "subscribersId")],
    )
    val subscriptions: Set<User>? = null,

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "subscriptions",
        joinColumns = [JoinColumn(name = "subscribersId")],
        inverseJoinColumns = [JoinColumn(name = "userId")],
    )
    val subscribers: Set<User>? = null,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    val id: Long? = null,
)
