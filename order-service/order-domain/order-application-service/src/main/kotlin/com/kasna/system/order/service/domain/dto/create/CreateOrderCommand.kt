package com.kasna.system.order.service.domain.dto.create

import org.jetbrains.annotations.NotNull
import java.math.BigDecimal
import java.util.UUID

class CreateOrderCommand(
    @NotNull
    val customerId: UUID,
    @NotNull
    val restaurantId: UUID,
    @NotNull
    val price: BigDecimal,
    @NotNull
    val items: List<OrderItem>,
    val address: OrderAddress
)