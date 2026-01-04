package com.kasna.system.order.service.domain.mapper

import com.kasna.system.domain.valueobject.CustomerId
import com.kasna.system.domain.valueobject.Money
import com.kasna.system.domain.valueobject.ProductId
import com.kasna.system.domain.valueobject.RestaurantId
import com.kasna.system.order.service.domain.dto.create.CreateOrderCommand
import com.kasna.system.order.service.domain.dto.create.CreateOrderResponse
import com.kasna.system.order.service.domain.dto.create.OrderAddress
import com.kasna.system.order.service.domain.entity.Order
import com.kasna.system.order.service.domain.entity.OrderItem
import com.kasna.system.order.service.domain.entity.Product
import com.kasna.system.order.service.domain.entity.Restaurant
import com.kasna.system.order.service.domain.valueobject.StreetAddress
import org.springframework.stereotype.Component
import java.util.*


@Component
class OrderDataMapper {
    fun createOrderCommandToRestaurant(createOrderCommand: CreateOrderCommand) = Restaurant(
        RestaurantId(createOrderCommand.restaurantId),
        products = createOrderCommand.items.map {
            Product(
                ProductId(it.productId),
            )
        }
    )

    fun createOrderCommandToOrder(createOrderCommand: CreateOrderCommand) = Order(
        customerId = CustomerId(createOrderCommand.customerId),
        restaurantId = RestaurantId(createOrderCommand.restaurantId),
        deliveryAddress = orderAddressToStreetAddress(createOrderCommand.address),
        price = Money(createOrderCommand.price),
        items = orderItemsToOrderItemEntities(createOrderCommand.items)

    )

    private fun orderAddressToStreetAddress(orderAddress: OrderAddress) = StreetAddress(
        UUID.randomUUID(),
        street = orderAddress.street,
        postalCode = orderAddress.postalCode,
        city = orderAddress.city,
    )

    fun orderToCreateOrderResponse(order: Order, message: String) = CreateOrderResponse(
        orderTrackingId = order.trackingId!!.value,
        orderStatus = order.orderStatus!!,
        message = message
    )

    fun orderItemsToOrderItemEntities(orderItems: List<com.kasna.system.order.service.domain.dto.create.OrderItem>): MutableList<OrderItem> {
        return orderItems.map { orderItem ->
            OrderItem(
                product = Product(ProductId(orderItem.productId)),
                price = Money(orderItem.price),
                quantity = orderItem.quantity,
                subTotal = Money(orderItem.subTotal),
            )
        }.toMutableList()
    }

}