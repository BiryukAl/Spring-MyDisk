package ru.kpfu.itis.mydisk.data.entity

import jakarta.persistence.*


@Entity
@Table(name = "file")
data class File(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    val id: Long,
    @Column(name = "title", nullable = false)
    val title: String,
    @Column(name = "description", nullable = true)
    val description: String,
    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "holderId", referencedColumnName = "id")
    val holder: User,
    @Column(name = "nameFile")
    val nameFile: String,
    @Column(name = "publicAccess")
    val publicAccess: Boolean,

    @ManyToMany(mappedBy = "file", fetch = FetchType.LAZY)
    val access: Set<User>,
)
