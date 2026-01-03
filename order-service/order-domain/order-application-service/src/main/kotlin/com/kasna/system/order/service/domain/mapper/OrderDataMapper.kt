package com.kasna.system.order.service.domain.mapper

import com.kasna.system.domain.valueobject.ProductId
import com.kasna.system.domain.valueobject.RestaurantId
import com.kasna.system.order.service.domain.dto.create.CreateOrderCommand
import com.kasna.system.order.service.domain.entity.Product
import com.kasna.system.order.service.domain.entity.Restaurant
import org.springframework.stereotype.Component

@Component
class OrderDataMapper {
    fun createOrderCommandToRestaurant(createOrderCommand: CreateOrderCommand) = Restaurant(
        RestaurantId(createOrderCommand.restaurantId),
        products = createOrderCommand.items.map { Product(
            ProductId(it.productId),
        )}
    )

    fun createOrderCommandToOrder(createOrderCommand: CreateOrderCommand) = Order(

    )
}