package com.kasna.system.order.service.domain.dto.track

import com.kasna.system.domain.valueobject.OrderStatus
import org.jetbrains.annotations.NotNull
import java.util.UUID

class TrackOrderResponse(
    @NotNull
    val orderTrackingId: UUID,
    @NotNull
    val orderStatus: OrderStatus,
    val failureMessages: List<String>,
)