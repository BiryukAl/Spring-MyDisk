package ru.kpfu.itis.mydisk

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}

// TODO: AddСайт должен быть помещён в Docker (сервер приложений и сервер СУБД в отдельных контейнерах).
//  Код необходимо выложить на Github Classroom.
