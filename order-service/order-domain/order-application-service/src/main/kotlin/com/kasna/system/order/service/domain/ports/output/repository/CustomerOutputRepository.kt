package com.kasna.system.order.service.domain.ports.output.repository

import java.util.UUID

interface CustomerOutputRepository {
    fun findCustomer(customerId: UUID?): Customer?
}