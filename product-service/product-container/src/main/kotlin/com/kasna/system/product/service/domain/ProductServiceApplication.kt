package com.kasna.system.product.service.domain

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EnableJpaRepositories(basePackages = ["com.kasna.system.product.service.dataaccess"])
@EntityScan(basePackages = ["com.kasna.system.product.service.dataaccess"])
@SpringBootApplication(scanBasePackages = ["com.kasna.system"])
open class ProductServiceApplication {
    companion object {
        @JvmStatic fun main(args: Array<String>) {
            SpringApplication.run(ProductServiceApplication::class.java, *args)
        }
    }
}