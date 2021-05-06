package dev.nashe.domain.repository

import dev.nashe.domain.model.product.Product

interface ProductPersistenceRepository {
    fun syncRemoteProducts()
}