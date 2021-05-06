package dev.nashe.data.mapper.reviews

import dev.nashe.data.entity.ReviewEntity
import dev.nashe.data.mapper.base.ResponseMapper
import dev.nashe.domain.model.review.Review
import javax.inject.Inject

class ReviewEntityMapper @Inject constructor() : ResponseMapper<ReviewEntity, Review> {
    override fun mapToDomain(entity: ReviewEntity): Review {
        return Review(
            productId = entity.productId,
            locale = entity.locale,
            rating = entity.rating,
            text = entity.text
        )
    }
}