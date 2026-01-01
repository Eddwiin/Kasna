package com.kasna.system.order.service.domain.dto.create

import org.jetbrains.annotations.NotNull
import java.math.BigDecimal
import java.util.UUID

class OrderItem(
    @NotNull
    val productId: UUID,
    @NotNull
    val quantity: Int,
    @NotNull
    val price: BigDecimal,
    @NotNull
    val subTotal: BigDecimal,
)