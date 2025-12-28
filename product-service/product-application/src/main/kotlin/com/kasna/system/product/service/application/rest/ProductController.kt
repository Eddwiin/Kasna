package com.kasna.system.product.service.application.rest

import com.kasna.system.product.service.domain.entity.Product
import com.kasna.system.product.service.domain.ports.input.service.ProductInputService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.data.domain.Pageable


@RestController
@RequestMapping("/products", produces = arrayOf("application/vnd.api.v1+json"))
class ProductController(
    val productInputService: ProductInputService
) {
    @GetMapping
    fun products(pageable: Pageable): ResponseEntity<List<Product>> {
        return ResponseEntity.ok(productInputService.getProducts(pageable.offset, pageable.pageSize, pageable.pageNumber));
    }
}