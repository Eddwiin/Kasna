package com.kasna.system.order.service.domain

import com.kasna.system.order.service.domain.dto.create.CreateOrderCommand
import com.kasna.system.order.service.domain.dto.create.CreateOrderResponse
import com.kasna.system.order.service.domain.entity.Order
import com.kasna.system.order.service.domain.entity.Restaurant
import com.kasna.system.order.service.domain.exception.OrderDomainException
import com.kasna.system.order.service.domain.mapper.OrderDataMapper
import com.kasna.system.order.service.domain.ports.output.repository.CustomerOutputRepository
import com.kasna.system.order.service.domain.ports.output.repository.OrderOutputRepository
import com.kasna.system.order.service.domain.ports.output.repository.RestaurantOutputRepository
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.transaction.annotation.Transactional
import java.util.*


open class OrderCreateCommandHandler(
    val customerOutputRepository: CustomerOutputRepository,
    val restaurantOutputRepository: RestaurantOutputRepository,
    val orderDataMapper: OrderDataMapper,
    val orderDomainService: OrderDomainService,
    val orderRepository: OrderOutputRepository,
) {
    private val log = KotlinLogging.logger {}

    @Transactional
    open fun createOrder(createOrderCommand: CreateOrderCommand): CreateOrderResponse {
        checkCustomer(createOrderCommand.customerId)
        val restaurant = checkRestaurant(createOrderCommand)
        val order = orderDataMapper.createOrderCommandToOrder(createOrderCommand)
        val orderCreatedEvent = orderDomainService.validateAndInitiateOrder(order, restaurant)
        saveOrder(order);
        log.info {  "Order is created with id: ${orderCreatedEvent.order.id.value}" }
        return orderDataMapper.orderToCreateOrderResponse(order, "Order created successfully")
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

    private fun saveOrder(order: Order): Order {
        val orderResult: Order? = orderRepository.save(order)
        if (orderResult == null) {
            log.error { "Could not save order!" }
            throw OrderDomainException("Could not save order!")
        }
        log.info { "Order is saved with id: ${ orderResult.id.value}" }
        return orderResult
    }
}