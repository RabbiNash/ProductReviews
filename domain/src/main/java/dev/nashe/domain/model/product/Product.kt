package dev.nashe.domain.model.product

import dev.nashe.domain.model.review.Review

data class Product(
    val currency: String,
    val description: String,
    val id: String,
    val imgUrl: String,
    val name: String,
    val price: Double,
    val reviews : List<Review>? = null
 )