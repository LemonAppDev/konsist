package com.example.mango

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MangoApplication

fun main(args: Array<String>) {
    runApplication<MangoApplication>(*args)
}
