package ru.kpfu.itis.mydisk.data.entity

import jakarta.persistence.*

@Entity
@Table(name = "comment")
data class Comment(
    @Column
    val body: String,
    @ManyToOne
    @JoinColumn(name = "holderId", referencedColumnName = "id")
    val holderId: User,
    @ManyToOne
    @JoinColumn(name = "postId", referencedColumnName = "id")
    val postId: Post,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
)
