package me.anjwoc.management

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class ManagementUserService

fun main(args: Array<String>) {
    runApplication<ManagementUserService>(*args)
}