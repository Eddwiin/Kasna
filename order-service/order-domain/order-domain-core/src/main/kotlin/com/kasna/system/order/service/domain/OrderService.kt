package com.kasna.system.order.service.domain

import com.kasna.system.domain.DomainConstants.Companion.UTC
import com.kasna.system.order.service.domain.entity.Order
import com.kasna.system.order.service.domain.entity.Restaurant
import com.kasna.system.order.service.domain.event.OrderCancelledEvent
import com.kasna.system.order.service.domain.event.OrderCreatedEvent
import com.kasna.system.order.service.domain.event.OrderPaidEvent
import com.kasna.system.order.service.domain.exception.OrderDomainException
import io.github.oshai.kotlinlogging.KotlinLogging
import java.time.ZoneId
import java.time.ZonedDateTime

class OrderService : OrderDomainService {
    private val log = KotlinLogging.logger {}

    override fun validateAndInitiateOrder(
        order: Order,
        restaurant: Restaurant
    ): OrderCreatedEvent {
        validateRestaurant(restaurant)
        setOrderProductInformation(order, restaurant)
        order.validateOrder()
        order.initializeOrder()
        log.info { "Order ${order.id} initialized" }
        return OrderCreatedEvent(order, ZonedDateTime.now(ZoneId.of(UTC)),)
    }

    override fun payOrder(order: Order): OrderPaidEvent {
        order.pay()
        log.info { "Order with id: ${order.id.value} is paid" }
        return OrderPaidEvent(order, ZonedDateTime.now(ZoneId.of(UTC)))
    }

    override fun approveOrder(order: Order) {
        order.approve()
        log.info { "Order with id: ${order.id.value} is approved" }
    }

    override fun cancelOrderPayment(
        order: Order,
        failureMessages: MutableList<String>
    ): OrderCancelledEvent {
        order.initCancel(failureMessages);
        log.info { "Order payment is cancelling for order id: ${order.id.value}" }
        return OrderCancelledEvent(order, ZonedDateTime.now(ZoneId.of(UTC)));
    }

    override fun cancelOrder(
        order: Order,
        failureMessages: MutableList<String>
    ) {
        order.cancel(failureMessages);
        log.info { "Order payment cancelled for order id: ${order.id.value}" }
    }

    private fun validateRestaurant(restaurant: Restaurant) {
        if (!restaurant.active) {
            throw OrderDomainException("Restaurant with id  ${restaurant.id.value} is currently not active!");
        }
    }

    private fun setOrderProductInformation(
        order: Order,
        restaurant: Restaurant
    ) {
        return order.items.forEach { orderItem ->
            restaurant.products.forEach { restaurantProduct ->
                {
                    val currentProduct = orderItem.product
                    if (currentProduct.equals(restaurantProduct)) {
                        currentProduct.updateWithConfirmedNameAndPrice(restaurantProduct.name!!,
                            restaurantProduct.price!!)
                    }
                }
            }
        }
    }
}