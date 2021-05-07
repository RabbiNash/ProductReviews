package dev.nashe.productreviews.mapper.review

import dev.nashe.domain.model.review.Review
import dev.nashe.productreviews.mapper.base.DomainMapper
import dev.nashe.productreviews.model.ReviewView

class ReviewDomainMapper : DomainMapper<Review, ReviewView> {
    override fun mapToDomain(view: ReviewView): Review {
        return Review(
            locale = view.locale,
            productId = view.productId,
            rating = view.rating,
            text = view.text
        )
    }
}