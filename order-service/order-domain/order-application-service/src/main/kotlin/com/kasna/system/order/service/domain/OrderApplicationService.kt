package com.kasna.system.order.service.domain

import com.kasna.system.order.service.domain.dto.create.CreateOrderCommand
import com.kasna.system.order.service.domain.dto.create.CreateOrderResponse
import com.kasna.system.order.service.domain.dto.track.TrackOrderQuery
import com.kasna.system.order.service.domain.dto.track.TrackOrderResponse
import com.kasna.system.order.service.domain.ports.input.service.OrderInputService
import org.springframework.stereotype.Component

@Component
class OrderApplicationService(
    val orderCreateCommandHandler: OrderCreateCommandHandler
): OrderInputService {
    override fun createOrder(createOrderCommand: CreateOrderCommand?): CreateOrderResponse {
        TODO("Not yet implemented")
    }

    override fun trackOrder(trackOrderQuery: TrackOrderQuery?): TrackOrderResponse? {
        TODO("Not yet implemented")
    }
}