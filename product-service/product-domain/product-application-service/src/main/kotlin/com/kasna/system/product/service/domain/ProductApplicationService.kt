package com.kasna.system.product.service.domain

import com.kasna.system.product.service.domain.entity.Product
import com.kasna.system.product.service.domain.ports.input.service.ProductInputService
import com.kasna.system.product.service.domain.ports.output.repository.ProductOutputRepository
import org.springframework.stereotype.Service

@Service
class ProductApplicationService(val productOutputRepository: ProductOutputRepository) : ProductInputService {
    override fun getProducts(offset: Long, pageSize: Int, pageNumber: Int): List<Product> {
        return productOutputRepository.getProducts(offset, pageSize, pageNumber)
    }
}