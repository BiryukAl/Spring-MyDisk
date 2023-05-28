package ru.kpfu.itis.mydisk.data.entity

import jakarta.persistence.*

@Entity
@Table(name = "post")
data class Post(
    @Column
    val title: String,
    @Column
    val description: String?,

    @Column
    val img: String? = null,

    @OneToMany(mappedBy = "postId")
    val comments: Set<Comment>? = null,

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "holderId", referencedColumnName = "id")
    val holderId: User? = null,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
)
