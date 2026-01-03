package com.kasna.system.order.service.domain.valueobject

import com.kasna.system.domain.valueobject.BaseId
import java.util.UUID

class TrackingId(value: UUID): BaseId<UUID>(value)