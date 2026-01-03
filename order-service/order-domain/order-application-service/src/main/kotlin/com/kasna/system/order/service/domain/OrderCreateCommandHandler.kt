package com.kasna.system.order.service.domain

import com.kasna.system.order.service.domain.dto.create.CreateOrderCommand
import com.kasna.system.order.service.domain.dto.create.CreateOrderResponse
import com.kasna.system.order.service.domain.entity.Restaurant
import com.kasna.system.order.service.domain.exception.OrderDomainException
import com.kasna.system.order.service.domain.mapper.OrderDataMapper
import com.kasna.system.order.service.domain.ports.output.repository.CustomerOutputRepository
import com.kasna.system.order.service.domain.ports.output.repository.RestaurantOutputRepository
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.core.annotation.Order
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

open class OrderCreateCommandHandler(
    val customerOutputRepository: CustomerOutputRepository,
    val restaurantOutputRepository: RestaurantOutputRepository,
    val orderDataMapper: OrderDataMapper
) {
    private val log = KotlinLogging.logger {}

    @Transactional
    open fun createOrder(createOrderCommand: CreateOrderCommand): CreateOrderResponse {
        checkCustomer(createOrderCommand.customerId)
        val restaurant = checkRestaurant(createOrderCommand)
        val order: Order = orderDataMapper.createOrderCommandToOrder(createOrderCommand)
        log.info {  "Order is created with id: {}" }
        TODO("")
    }

    private fun checkRestaurant(createOrderCommand: CreateOrderCommand): Restaurant {
        val restaurant = orderDataMapper.createOrderCommandToRestaurant(createOrderCommand)
        val optionalRestaurant: Restaurant? = restaurantOutputRepository.findRestaurantInformation(restaurant)

        if (optionalRestaurant == null) {
            log.warn { "Could not find restaurant with restaurant id: ${createOrderCommand.restaurantId}" };
            throw OrderDomainException("Could not find restaurant with restaurant id: ${createOrderCommand.restaurantId}")
        }

        return restaurant
    }

    private fun checkCustomer(customerId: UUID) {
        val customer = customerOutputRepository.findCustomer(customerId);
        if(customer == null) {
            log.warn {  "Could not find customer with customer id: $customerId"}
            throw OrderDomainException("Could not find customer with customer id: " + customer);
        }
    }
}