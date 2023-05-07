package ru.kpfu.itis.mydisk.data.repositories

import org.springframework.data.jpa.repository.JpaRepository
import ru.kpfu.itis.mydisk.data.entity.File

interface FileRepository : JpaRepository<File, Long>
