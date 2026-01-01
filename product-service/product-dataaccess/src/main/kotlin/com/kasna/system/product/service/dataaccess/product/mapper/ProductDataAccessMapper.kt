package com.kasna.system.product.service.dataaccess.product.mapper

import com.kasna.system.domain.valueobject.ProductId
import com.kasna.system.product.service.dataaccess.product.entity.ProductEntity
import com.kasna.system.product.service.domain.entity.Product
import org.springframework.stereotype.Component

@Component
class ProductDataAccessMapper {
    fun productEntityToProduct(productEntity: ProductEntity) = Product(
        id = ProductId(productEntity.id!!),
        name = productEntity.name,
        description = productEntity.description,
        price = productEntity.price,
        stockQuantity = productEntity.stockQuantity,
        active = productEntity.active,
        imageUrl = productEntity.imageUrl,
        createdAt = productEntity.createdAt,
        updatedAt = productEntity.updatedAt,
    )
}