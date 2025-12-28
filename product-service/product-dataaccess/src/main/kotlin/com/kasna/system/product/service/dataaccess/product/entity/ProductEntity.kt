package com.kasna.system.product.service.dataaccess.product.entity

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "product")
class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(nullable = false)
    private val name: String? = null

    @Column(length = 1000)
    private val description: String? = null

    @Column(nullable = false)
    private val price: BigDecimal? = null

    @Column(nullable = false)
    private val stockQuantity = 0

    private val imageUrl: String? = null

    @Column(nullable = false)
    private val active = true

    private var createdAt: LocalDateTime? = null

    private var updatedAt: LocalDateTime? = null


    @PrePersist
    protected fun onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
    }

    @PreUpdate
    protected fun onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}