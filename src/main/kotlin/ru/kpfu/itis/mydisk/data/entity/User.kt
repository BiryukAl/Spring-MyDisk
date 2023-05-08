package ru.kpfu.itis.mydisk.data.entity

import jakarta.persistence.*
import ru.kpfu.itis.mydisk.domain.security.Role

@Entity
@Table(name = "usr")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    val id: Long,
    @Column(name = "name")
    val name: String,
    @Column(name = "email", unique = true)
    val email: String,
    @Column(name = "password")
    val password: String,
    @ManyToMany()
    val file: Set<File>,

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "subscriptions",
        joinColumns = [JoinColumn(name = "userId")],
        inverseJoinColumns = [JoinColumn(name = "subscribersId")],
    )
    val subscriptions: Set<User>,

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "subscriptions",
        joinColumns = [JoinColumn(name = "subscribersId")],
        inverseJoinColumns = [JoinColumn(name = "userId")],
    )
    val subscribers: Set<User>,

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    val role: Role,
)