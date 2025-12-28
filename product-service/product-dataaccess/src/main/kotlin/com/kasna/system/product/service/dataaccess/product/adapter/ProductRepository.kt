package com.kasna.system.product.service.dataaccess.product.adapter

import com.kasna.system.product.service.domain.entity.Product
import com.kasna.system.product.service.dataaccess.product.repository.ProductJpaRepository
import com.kasna.system.product.service.domain.ports.output.repository.ProductOutputRepository
import org.springframework.stereotype.Component

@Component
class ProductRepository(val productJpaRepository: ProductJpaRepository): ProductOutputRepository {
    override fun getProducts(offset: Long, pageSize: Int, pageNumber: Int): List<Product> {
        return productJpaRepository.findAll(offset, pageSize, pageNumber).toList()
    }
}