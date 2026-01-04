package com.kasna.system.order.service.domain

import com.kasna.system.order.service.domain.entity.Order
import com.kasna.system.order.service.domain.entity.Restaurant
import com.kasna.system.order.service.domain.event.OrderCancelledEvent
import com.kasna.system.order.service.domain.event.OrderCreatedEvent
import com.kasna.system.order.service.domain.event.OrderPaidEvent

interface OrderDomainService {
    fun validateAndInitiateOrder(order: Order, restaurant: Restaurant): OrderCreatedEvent

    fun payOrder(order: Order): OrderPaidEvent

    fun approveOrder(order: Order)

    fun cancelOrderPayment(
        order: Order,
        failureMessages: MutableList<String>,
    ): OrderCancelledEvent?

    fun cancelOrder(order: Order, failureMessages: MutableList<String>)

}