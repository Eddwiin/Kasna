package com.kasna.system.product.service.domain.ports.output.repository

import com.kasna.system.product.service.domain.entity.Product

interface ProductOutputRepository {
    fun getProducts(offset: Long, pageSize: Int, pageNumber: Int): List<Product>
}