package com.kasna.system.order.service.domain

import com.kasna.system.order.service.domain.dto.create.CreateOrderCommand
import com.kasna.system.order.service.domain.dto.create.CreateOrderResponse
import com.kasna.system.order.service.domain.entity.Restaurant
import com.kasna.system.order.service.domain.exception.OrderDomainException
import com.kasna.system.order.service.domain.ports.output.repository.CustomerOutputRepository
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

open class OrderCreateCommandHandler(
    val customerOutputRepository: CustomerOutputRepository
) {
    private val log = KotlinLogging.logger {}

    @Transactional
    open fun createOrder(createOrderCommand: CreateOrderCommand): CreateOrderResponse {
        checkCustomer(createOrderCommand.customerId)
        val restaurant = checkRestaurant(createOrderCommand)
        log.info {  "Order is created with id: {}" }
        TODO("")
    }

    private fun checkRestaurant(createOrderCommand: CreateOrderCommand): Restaurant {
        TODO("Not yet implemented")
    }

    private fun checkCustomer(customerId: UUID) {
        val customer = customerOutputRepository.findCustomer(customerId);
        if(customer == null) {
            log.warn {  "Could not find customer with customer id: $customerId"}
            throw OrderDomainException("Could not find customer with customer id: " + customer);
        }
    }
}