package ru.kpfu.itis.mydisk.data.entity

import jakarta.persistence.*


@Entity
@Table(name = "file")
data class File(

    @Column(name = "title", nullable = false)
    val title: String,
    @Column(name = "description", nullable = true)
    val description: String?,

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "holderId", referencedColumnName = "id", nullable = false)
    val holderId: User,

    @Column(name = "nameFile")
    val nameFile: String,
    @Column(name = "publicAccess")
    val publicAccess: Boolean,

    @ManyToMany
    var collectionFiles: Set<CollectionFiles>? = null,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    var id: Long? = null,
)
