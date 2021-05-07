package dev.nashe.data.api

import dev.nashe.data.entity.ProductEntity
import dev.nashe.domain.model.product.Product
import retrofit2.http.GET

interface ApiService {

    @GET("product")
    suspend fun getProducts() : List<Product>

}