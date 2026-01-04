package com.kasna.system.order.service.domain.entity

import com.kasna.system.domain.entity.BaseEntity
import com.kasna.system.domain.valueobject.Money
import com.kasna.system.domain.valueobject.OrderId
import com.kasna.system.order.service.domain.valueobject.OrderItemId


class OrderItem(
    val product: Product,
    val quantity: Int = 0,
    val price: Money,
    val subTotal: Money,
) : BaseEntity<OrderItemId>(OrderItemId(0)) {
    private lateinit var orderItemId: OrderItemId
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