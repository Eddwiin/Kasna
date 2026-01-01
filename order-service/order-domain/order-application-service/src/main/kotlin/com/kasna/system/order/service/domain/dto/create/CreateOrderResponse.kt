package com.kasna.system.order.service.domain.dto.create

import com.kasna.system.domain.valueobject.OrderStatus
import java.util.UUID

class CreateOrderResponse(
    val orderTrackingId: UUID,
    val orderStatus: OrderStatus,
    val message: String
)