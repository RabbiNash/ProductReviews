package dev.nashe.productreviews.repos

import dev.nashe.domain.model.review.Review
import dev.nashe.domain.repository.ReviewRepository
import dev.nashe.productreviews.data.DataStub

class MockReviewRepository : ReviewRepository {
    override suspend fun createReview(review: Review) {

    }

    override suspend fun getReviews(id: String): List<Review> =  listOf(DataStub.review)
}