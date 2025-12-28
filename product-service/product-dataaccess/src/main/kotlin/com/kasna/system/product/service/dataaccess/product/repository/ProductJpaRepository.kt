package com.kasna.system.product.service.dataaccess.product.repository

import com.kasna.system.product.service.domain.entity.Product
import org.springframework.data.domain.Page
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ProductJpaRepository: JpaRepository<Product, UUID> {
    @Query(value = "SELECT * FROM product ORDER BY name LIMIT :pageSize OFFSET :offset", nativeQuery = true)
    fun findAll(offset: Long, pageSize: Int, pageNumber: Int): Page<Product>
}