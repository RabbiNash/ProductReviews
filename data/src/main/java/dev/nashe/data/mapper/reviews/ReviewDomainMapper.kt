package dev.nashe.data.mapper.reviews

import dev.nashe.data.entity.ReviewEntity
import dev.nashe.data.mapper.base.RequestMapper
import dev.nashe.domain.model.review.Review
import javax.inject.Inject

class ReviewDomainMapper @Inject constructor() :  RequestMapper<Review, ReviewEntity>{

    override fun mapToEntity(domain: Review): ReviewEntity {
        return ReviewEntity(
                productId = domain.productId,
                locale = domain.locale,
                rating = domain.rating,
                text = domain.text
        )
    }
}