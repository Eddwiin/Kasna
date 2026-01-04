package com.kasna.system.order.service.domain.entity

import com.kasna.system.domain.entity.AggregateRoot
import com.kasna.system.domain.valueobject.*
import com.kasna.system.order.service.domain.exception.OrderDomainException
import com.kasna.system.order.service.domain.valueobject.OrderItemId
import com.kasna.system.order.service.domain.valueobject.StreetAddress
import com.kasna.system.order.service.domain.valueobject.TrackingId
import java.util.*


class Order(
    val customerId: CustomerId? = null,
    val restaurantId: RestaurantId? = null,
    val deliveryAddress: StreetAddress? = null,
    val price: Money,
    val trackingId: TrackingId? = null,
    var orderStatus: OrderStatus? = null,
    val items: MutableList<OrderItem> = mutableListOf()
) : AggregateRoot<OrderId>(OrderId(UUID.randomUUID())) {
    private var failureMessages: MutableList<String> = mutableListOf()

    companion object {
        val FAILURE_MESSAGE_DELIMITER = ","
    }

    fun pay() {
        if (orderStatus != OrderStatus.PENDING) {
            throw OrderDomainException("Order is not in correct state for pay operation!")
        }
        orderStatus = OrderStatus.PAID
    }

    fun approve() {
        if (orderStatus != OrderStatus.PAID) {
            throw OrderDomainException("Order is not in correct state for approve operation!")
        }
        orderStatus = OrderStatus.APPROVED
    }

    fun initCancel(failureMessages: MutableList<String>?) {
        if (orderStatus != OrderStatus.PAID) {
            throw OrderDomainException("Order is not in correct state for initCancel operation!")
        }
        orderStatus = OrderStatus.CANCELLING
        updateFailureMessages(failureMessages)
    }

    fun cancel(failureMessages: MutableList<String>?) {
        if (!(orderStatus == OrderStatus.CANCELLING || orderStatus == OrderStatus.PENDING)) {
            throw OrderDomainException("Order is not in correct state for cancel operation!")
        }
        orderStatus = OrderStatus.CANCELLED
        updateFailureMessages(failureMessages)
    }

    private fun updateFailureMessages(failureMessages: MutableList<String>?) {
        failureMessages
            ?.filter { it.isNotEmpty() }
            ?.let { failureMessages.addAll(it) }
    }

    private fun validateInitialOrder() {
        if (orderStatus != null || id != null) {
            throw OrderDomainException("Order is not in correct state for initialization!")
        }
    }

    private fun validateTotalPrice() {
        if (price == null || !price.isGreaterThanZero()) {
            throw OrderDomainException("Total price must be greater than zero!")
        }
    }

    private fun validateItemsPrice() {
        val orderItemsTotal: Money =
            items.map { orderItem ->
                validateItemPrice(orderItem)
                orderItem.subTotal
            }.fold(Money.ZERO) { acc, money -> acc.add(money) }

        if (!price.equals(orderItemsTotal)) {
            throw  OrderDomainException("Total price: " + price.getAmount()
                    + " is not equal to Order items total: " + orderItemsTotal.getAmount() + "!");
        }
    }

    private fun validateItemPrice(orderItem: OrderItem) {
        if (!orderItem.isPriceValid()) {
            throw OrderDomainException(
                "Order item price: ${orderItem.price.amount} is not valid for product ${orderItem.product.productId.value}"
            )
        }
    }

    private fun initializeOrderItems() {
        var itemId: Long = 1
        for (orderItem in items) {
            orderItem.initializeOrderItem(super.id, OrderItemId(itemId++))
        }
    }

    fun validateOrder() {
        validateInitialOrder();
        validateTotalPrice();
        validateItemsPrice();
    }

    fun initializeOrder() {
        var itemId: Long = 1
        for (orderItem in items) {
            orderItem.initializeOrderItem(super.id, OrderItemId(itemId++))
        }
    }
}

