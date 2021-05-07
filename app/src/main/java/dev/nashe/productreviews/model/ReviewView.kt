package dev.nashe.productreviews.model

data class ReviewView( val locale: String,
                       val productId: String,
                       val rating: Int,
                       val text: String )