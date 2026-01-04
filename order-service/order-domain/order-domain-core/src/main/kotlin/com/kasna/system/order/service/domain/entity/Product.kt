package com.kasna.system.order.service.domain.entity

import com.kasna.system.domain.valueobject.Money
import com.kasna.system.domain.valueobject.ProductId

class Product(val productId: ProductId, var name: String? = null, var price: Money? = null) {
    fun updateWithConfirmedNameAndPrice(name: String, price: Money) {
        this.name = name
        this.price = price
    }
}