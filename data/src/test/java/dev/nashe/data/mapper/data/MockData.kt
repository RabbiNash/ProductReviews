package dev.nashe.data.mapper.data

import dev.nashe.data.entity.ProductEntity
import dev.nashe.data.entity.ReviewEntity

internal object MockData {

        val review = ReviewEntity(locale = "en-US", productId = "H1347",rating =  4, text ="this product is the bestaaa" , id = "123")

        private val fakeReviews = listOf<ReviewEntity>(review)

        val fakeProduct = ProductEntity(
                currency = "euro",
                price = 5.0,
                id = "HI347",
                name = "product",
                imgUrl = "https://assets.adidas.com/images/w_320,h_320,f_auto,q_auto:sensitive,fl_lossy/0fcb1719675b4df0a160ab1a0104630b_9366/GD3509_01_laydown.jpg",
                description = "Its a really nice product"
        )
}