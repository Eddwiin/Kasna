package com.kasna.system.product.service.dataaccess.product.adapter

import com.kasna.system.product.service.dataaccess.product.mapper.ProductDataAccessMapper
import com.kasna.system.product.service.domain.entity.Product
import com.kasna.system.product.service.dataaccess.product.repository.ProductJpaRepository
import com.kasna.system.product.service.domain.ports.output.repository.ProductOutputRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component

@Component
class ProductRepository(
    val productJpaRepository: ProductJpaRepository,
    val productDataAccessMapper: ProductDataAccessMapper
) : ProductOutputRepository {
    override fun getProducts(offset: Long, pageSize: Int, pageNumber: Int): List<Product> {
        val pageable = PageRequest.of(pageNumber, pageSize)
        return productJpaRepository.findAll(pageable)
            .map { productDataAccessMapper.productEntityToProduct(it) }.toList()
    }
}