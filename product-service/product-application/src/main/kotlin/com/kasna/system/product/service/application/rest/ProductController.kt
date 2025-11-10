package com.kasna.system.product.service.application.rest

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/product", produces = arrayOf("application/vnd.api.v1+json"))
class ProductController {
    @GetMapping
    fun hello(): ResponseEntity<String> {
        return ResponseEntity.ok("Hello World")
    }
}