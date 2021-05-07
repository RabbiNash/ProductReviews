package dev.nashe.productreviews.model

data class ProductView( val currency: String,
                         val description: String,
                         val id: String,
                         val imgUrl: String,
                         val name: String,
                         val price: Int)