package com.kasna.system.order.service.domain.entity

import com.kasna.system.domain.entity.BaseEntity
import com.kasna.system.domain.valueobject.Money
import com.kasna.system.domain.valueobject.OrderId
import com.kasna.system.order.service.domain.valueobject.OrderItemId


class OrderItem(
    private var orderItemId: OrderItemId,
    val product: Product,
    val quantity: Int = 0,
    val price: Money,
    val subTotal: Money,
) : BaseEntity<OrderItemId>(orderItemId) {
    private lateinit var orderId: OrderId

    fun isPriceValid(): Boolean {
        return price.isGreaterThanZero() &&
                price == product.price &&
                price.multiply(quantity) == subTotal
    }

    fun initializeOrderItem(id: OrderId, orderItemId: OrderItemId) {
        this.orderId = id
        this.orderItemId = orderItemId
    }
}