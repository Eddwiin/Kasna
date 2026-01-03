package com.kasna.system.order.service.domain.ports.output.repository

import com.kasna.system.order.service.domain.entity.Order

interface OrderOutputRepository {
    fun save(order: Order): Order?
}