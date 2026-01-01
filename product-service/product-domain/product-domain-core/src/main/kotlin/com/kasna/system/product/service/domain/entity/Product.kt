package com.kasna.system.product.service.domain.entity

import com.kasna.system.domain.entity.BaseEntity
import com.kasna.system.domain.valueobject.ProductId
import java.math.BigDecimal
import java.time.LocalDateTime

class Product(
    id: ProductId,
    name: String? = null,
    description: String? = null,
    price: BigDecimal? = null,
    stockQuantity: Int? = 0,
    imageUrl: String? = null,
    active: Boolean = true,
    createdAt: LocalDateTime? = null,
    updatedAt: LocalDateTime? = null
) : BaseEntity<ProductId>(id)