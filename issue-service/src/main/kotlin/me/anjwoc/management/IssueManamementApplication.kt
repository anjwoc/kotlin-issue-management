package me.anjwoc.management

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.event.EventListener
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@SpringBootApplication
class IssueManamementApplication

@EventListener(AuditingEntityListener::class)
fun main(args: Array<String>) {
    runApplication<IssueManamementApplication>(*args)
}
