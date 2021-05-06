package dev.nashe.domain.repository.sync.product

import dev.nashe.domain.model.product.Product

interface ProductSynCallback {
    fun getProductSyncSuccess(products: List<Product>)

    fun getProductSyncFailure()
}