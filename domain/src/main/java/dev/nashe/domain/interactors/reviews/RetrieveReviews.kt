package dev.nashe.domain.interactors.reviews

import dev.nashe.domain.repository.ReviewRepository
import javax.inject.Inject

class RetrieveReviews @Inject constructor(
    private val reviewRepository: ReviewRepository
) {
    suspend operator fun invoke(id : String) = reviewRepository.getReviews(id)
}