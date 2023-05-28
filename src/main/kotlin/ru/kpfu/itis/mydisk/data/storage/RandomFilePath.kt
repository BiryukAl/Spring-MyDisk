package ru.kpfu.itis.mydisk.data.storage

import java.util.*

object RandomFilePath {
    fun generateFileName(oldNameFile: String): String {
        val split =
            oldNameFile.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val suffix = split[split.size - 1]
        return getRandomString() + "." + suffix
    }

    private fun getRandomString(size: Long = 10L): String {
        val leftLimit = 97 // letter 'a'
        val rightLimit = 122 // letter 'z'
        val random = Random()
        return random.ints(leftLimit, rightLimit + 1)
            .limit(size)
            .collect({ StringBuilder() }, java.lang.StringBuilder::appendCodePoint, java.lang.StringBuilder::append)
            .toString()
    }
}
