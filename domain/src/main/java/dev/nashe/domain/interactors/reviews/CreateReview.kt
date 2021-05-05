package dev.nashe.domain.interactors.reviews

import dev.nashe.domain.model.review.Review
import dev.nashe.domain.repository.ReviewRepository
import javax.inject.Inject

class CreateReview @Inject constructor(
    private val reviewRepository: ReviewRepository
) {
    suspend operator fun invoke(review: Review) = reviewRepository.createReview(review)
}