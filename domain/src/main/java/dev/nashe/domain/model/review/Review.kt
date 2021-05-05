package dev.nashe.domain.model.review

data class Review(
    val locale: String,
    val productId: String,
    val rating: Int,
    val text: String
)