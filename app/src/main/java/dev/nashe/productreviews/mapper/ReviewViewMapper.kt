package dev.nashe.productreviews.mapper

import dev.nashe.domain.model.review.Review
import dev.nashe.productreviews.mapper.base.Mapper
import dev.nashe.productreviews.model.ReviewView
import javax.inject.Inject

class ReviewViewMapper @Inject constructor() : Mapper<ReviewView, Review> {

    override fun mapToView(domain: Review): ReviewView {
        return ReviewView(
            locale = domain.locale,
            productId = domain.productId,
            rating = domain.rating,
            text = domain.text
        )
    }
}