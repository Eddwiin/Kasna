package com.kasna.system.order.service.domain.valueobject

import java.util.*

data class StreetAddress(
    val id: UUID? = null,
    val street: String? = null,
    val postalCode: String? = null,
    val city: String? = null
)