package com.kasna.system.order.service.domain

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan

//@EnableJpaRepositories(basePackages = ["com.kasna.system.order.service.dataaccess"])
@EntityScan(basePackages = ["com.kasna.system.order.service.dataaccess"])
@SpringBootApplication(scanBasePackages = ["com.kasna.system"])
 class OrderServiceApplication {
    companion object {
        @JvmStatic fun main(args: Array<String>) {
            SpringApplication.run(OrderServiceApplication::class.java, *args)
        }
    }
}