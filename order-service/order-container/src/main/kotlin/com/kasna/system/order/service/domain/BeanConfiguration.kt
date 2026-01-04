package com.kasna.system.order.service.domain

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BeanConfiguration {
    @Bean
    open fun orderDomainService(): OrderDomainService {
        return OrderService()
    }
}