package com.chenfangwei.octopus.core

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.config.EnableMongoAuditing

@SpringBootApplication
@EnableMongoAuditing
class CoreApplication

fun main(args: Array<String>) {
	runApplication<CoreApplication>(*args)
}
