package dev.nashe.domain

import dev.nashe.domain.model.product.Product
import dev.nashe.domain.model.review.Review

internal object DataStub {

    val review = Review("en-US", "H1347", 4, "this product is the bestaaa" )
    val productName = "product"
    private val fakeReviews = listOf<Review>(review)

    val fakeProduct = Product(
        currency = "euro",
        price = 5.0,
        id = "HI347",
        name = "product",
        imgUrl = "https://assets.adidas.com/images/w_320,h_320,f_auto,q_auto:sensitive,fl_lossy/0fcb1719675b4df0a160ab1a0104630b_9366/GD3509_01_laydown.jpg",
        description = "Its a really nice product"
    )

}