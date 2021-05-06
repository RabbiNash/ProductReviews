package dev.nashe.data.mapper.products

import dev.nashe.data.entity.ProductEntity
import dev.nashe.data.mapper.base.ResponseMapper
import dev.nashe.domain.model.product.Product
import javax.inject.Inject

class ProductEntityMapper @Inject constructor() : ResponseMapper<ProductEntity, Product> {
    override fun mapToDomain(entity: ProductEntity): Product {
        return Product(
            name = entity.name,
            id = entity.id,
            currency = entity.currency,
            description = entity.description,
            price = entity.price,
            imgUrl = entity.imgUrl,
            reviews = entity.reviews
        )
    }
}