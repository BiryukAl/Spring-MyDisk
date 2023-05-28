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

    @OneToMany(mappedBy = "holderId", orphanRemoval = true, fetch = FetchType.EAGER)
    var file: Set<File>? = null,
    // TODO: Fix get all files user 

    @OneToMany(mappedBy = "holderId")
    var post: Set<Post>? = null,

    @OneToMany(mappedBy = "holderId")
    var collectionFiles: Set<CollectionFiles>? = null,

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "subscriptions",
        joinColumns = [JoinColumn(name = "userId")],
        inverseJoinColumns = [JoinColumn(name = "subscribersId")],
    )
    var subscriptions: Set<User>? = null,

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "subscriptions",
        joinColumns = [JoinColumn(name = "subscribersId")],
        inverseJoinColumns = [JoinColumn(name = "userId")],
    )
    var subscribers: Set<User>? = null,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    val id: Long? = null,
)
