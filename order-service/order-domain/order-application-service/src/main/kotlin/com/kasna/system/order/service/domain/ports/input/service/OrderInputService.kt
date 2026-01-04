package com.kasna.system.order.service.domain.ports.input.service

import com.kasna.system.order.service.domain.dto.create.CreateOrderCommand
import com.kasna.system.order.service.domain.dto.create.CreateOrderResponse
import com.kasna.system.order.service.domain.dto.track.TrackOrderQuery
import com.kasna.system.order.service.domain.dto.track.TrackOrderResponse
import jakarta.validation.Valid


interface OrderInputService {
    fun createOrder(@Valid createOrderCommand: CreateOrderCommand): CreateOrderResponse

    fun trackOrder(@Valid trackOrderQuery: TrackOrderQuery?): TrackOrderResponse?
}