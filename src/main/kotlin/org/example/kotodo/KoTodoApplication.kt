package org.example.kotodo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KoTodoApplication

fun main(args: Array<String>) {
    runApplication<KoTodoApplication>(*args)
}
