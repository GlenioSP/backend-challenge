package com.invillia.acme

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class InvilliaApplication

fun main(args: Array<String>) {
    SpringApplication.run(InvilliaApplication::class.java, *args)
}