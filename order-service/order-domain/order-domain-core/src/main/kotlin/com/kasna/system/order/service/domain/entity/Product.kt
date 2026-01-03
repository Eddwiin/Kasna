package com.kasna.system.order.service.domain.entity

import com.kasna.system.domain.valueobject.Money
import com.kasna.system.domain.valueobject.ProductId

class Product(val productId: ProductId, val name: String? = null, val price: Money? = null)