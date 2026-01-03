package com.kasna.system.order.service.domain

import com.kasna.system.order.service.domain.entity.Order
import com.kasna.system.order.service.domain.entity.Restaurant
import com.kasna.system.order.service.domain.event.OrderCreatedEvent

interface OrderDomainService {
    fun validateAndInitiateOrder(order: Order, restaurant: Restaurant): OrderCreatedEvent {}
}