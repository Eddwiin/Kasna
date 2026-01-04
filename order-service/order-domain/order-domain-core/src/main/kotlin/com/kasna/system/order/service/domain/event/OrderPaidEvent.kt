package com.kasna.system.order.service.domain.event

import com.kasna.system.order.service.domain.entity.Order
import java.time.ZonedDateTime

class OrderPaidEvent(order: Order, createdAt: ZonedDateTime): OrderEvent(order, createdAt) {
    override fun fire() {
        TODO("Not yet implemented")
    }
}