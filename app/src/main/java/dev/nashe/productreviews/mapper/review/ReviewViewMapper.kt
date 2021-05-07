package dev.nashe.productreviews.mapper.review

import dev.nashe.domain.model.review.Review
import dev.nashe.productreviews.mapper.base.ViewMapper
import dev.nashe.productreviews.model.ReviewView
import javax.inject.Inject

class ReviewViewMapper @Inject constructor() : ViewMapper<ReviewView, Review> {

    override fun mapToView(domain: Review): ReviewView {
        return ReviewView(
            locale = domain.locale,
            productId = domain.productId,
            rating = domain.rating,
            text = domain.text
        )
    }
}