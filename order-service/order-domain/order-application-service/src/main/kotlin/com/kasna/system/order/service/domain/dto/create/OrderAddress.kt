package com.kasna.system.order.service.domain.dto.create

import jakarta.validation.constraints.Max
import org.jetbrains.annotations.NotNull

data class OrderAddress(
    @field:NotNull
    @field:Max(value = 50)
    val street: String,

    @field:NotNull
    @Max(value = 10)
    val postalCode: String,

    @field:NotNull
    @field:Max(value = 50)
    val city: String,
)