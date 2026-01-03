package com.kasna.system.order.service.domain.ports.output.repository

import com.kasna.system.order.service.domain.entity.Restaurant

interface RestaurantOutputRepository {
    fun findRestaurantInformation(restaurant: Restaurant): Restaurant?
}