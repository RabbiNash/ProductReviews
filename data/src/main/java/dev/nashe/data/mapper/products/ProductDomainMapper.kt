package dev.nashe.data.mapper.products

import dev.nashe.data.entity.ProductEntity
import dev.nashe.data.mapper.base.RequestMapper
import dev.nashe.domain.model.product.Product
import javax.inject.Inject

class ProductDomainMapper @Inject constructor() : RequestMapper<Product, ProductEntity> {

    override fun mapToEntity(domain: Product): ProductEntity {
        return ProductEntity(
                id = domain.id,
                name = domain.name,
                currency = domain.currency,
                imgUrl = domain.imgUrl,
                description = domain.description,
                price = domain.price,
                reviews = domain.reviews
        )
    }

}