package com.kasna.system.order.service.domain.dto.track

import org.jetbrains.annotations.NotNull
import java.util.UUID

class TrackOrderQuery(
    @NotNull
    val orderTrackingId: UUID
)