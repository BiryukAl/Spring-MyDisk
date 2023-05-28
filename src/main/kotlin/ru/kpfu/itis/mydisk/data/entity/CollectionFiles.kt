package ru.kpfu.itis.mydisk.data.entity

import jakarta.persistence.*

@Entity
@Table(name = "collection")
data class CollectionFiles(
    @Column
    val title: String,
    @Column
    val description: String,

    @ManyToMany
    @JoinTable(
        name = "collection_file",
        joinColumns = [JoinColumn(name = "collection_id")],
        inverseJoinColumns = [JoinColumn(name = "file_id")]
    )
    val files: Set<File>? = null,

    @ManyToOne
    @JoinColumn(name = "holderId", referencedColumnName = "id")
    val holderId: User,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
)
