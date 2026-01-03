package com.kasna.system.order.service.domain.event

import com.kasna.system.domain.event.DomainEvent
import com.kasna.system.order.service.domain.entity.Order
import java.time.ZonedDateTime

abstract class OrderEvent(
    open var order: Order,
    open var createdAt: ZonedDateTime
): DomainEvent<Order>