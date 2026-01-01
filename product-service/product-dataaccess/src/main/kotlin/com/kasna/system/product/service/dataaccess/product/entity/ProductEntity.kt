package com.kasna.system.product.service.dataaccess.product.entity

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "product")
class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: UUID? = null

    @Column(nullable = false)
    val name: String? = null

    @Column(length = 1000)
    val description: String? = null

    @Column(nullable = false)
    val price: BigDecimal? = null

    @Column(nullable = false)
    val stockQuantity = 0

    @Column(nullable = false)
    val imageUrl: String? = null

    @Column(nullable = false)
    val active = true

    @Column(nullable = false)
    var createdAt: LocalDateTime? = null

    @Column(nullable = false)
    var updatedAt: LocalDateTime? = null


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