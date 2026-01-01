package com.kasna.system.order.service.application.rest

import com.kasna.system.order.service.domain.dto.create.CreateOrderCommand
import com.kasna.system.order.service.domain.dto.create.CreateOrderResponse
import com.kasna.system.order.service.domain.ports.input.service.OrderInputService
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/orders")
class OrderController(
    val orderInputService: OrderInputService
) {
    private val log = KotlinLogging.logger {}

    @PostMapping
    fun createOrder(@RequestBody createOrderCommand: CreateOrderCommand): ResponseEntity<CreateOrderResponse> {
        log.info {"Creating order for customer: ${createOrderCommand.customerId} at restaurant: ${createOrderCommand.restaurantId}" }
        val createOrderResponse = orderInputService.createOrder(createOrderCommand)
        log.info { "Order created with tracking id: ${createOrderResponse.orderTrackingId}" }
        return ResponseEntity.ok(createOrderResponse)
    }
}