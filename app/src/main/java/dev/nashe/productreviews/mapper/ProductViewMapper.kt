package dev.nashe.productreviews.mapper

import dev.nashe.domain.model.product.Product
import dev.nashe.productreviews.mapper.base.Mapper
import dev.nashe.productreviews.model.ProductView
import javax.inject.Inject

class ProductViewMapper @Inject constructor() : Mapper<ProductView, Product> {

    override fun mapToView(domain: Product): ProductView {
        return ProductView(
            id = domain.id,
            name = domain.name,
            price = domain.price,
            imgUrl = domain.imgUrl,
            currency = domain.currency,
            description = domain.description
        )
    }
}