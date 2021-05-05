package dev.nashe.domain.repository

import dev.nashe.domain.model.product.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun getProducts(): List<Product>

    suspend fun searchProduct(param : String): Flow<List<Product>>

}