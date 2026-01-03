package com.kasna.system.order.service.domain.ports.output.repository

import com.kasna.system.order.service.domain.entity.Customer
import com.kasna.system.order.service.domain.entity.Restaurant
import java.util.UUID

interface CustomerOutputRepository {
    fun findCustomer(customerId: UUID?): Customer?
}