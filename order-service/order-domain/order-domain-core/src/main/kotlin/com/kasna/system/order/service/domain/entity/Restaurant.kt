package com.kasna.system.order.service.domain.entity

import com.kasna.system.domain.entity.AggregateRoot
import com.kasna.system.domain.valueobject.RestaurantId

class Restaurant(val restaurantId: RestaurantId, val products: List<Product>, val active: Boolean) :
    AggregateRoot<RestaurantId>(restaurantId)