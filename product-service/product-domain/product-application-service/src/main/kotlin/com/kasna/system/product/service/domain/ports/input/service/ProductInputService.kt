package com.kasna.system.product.service.domain.ports.input.service

import com.kasna.system.product.service.domain.entity.Product

interface ProductInputService {
    fun getProducts(offset: Long, pageSize: Int, pageNumber: Int): List<Product>
}