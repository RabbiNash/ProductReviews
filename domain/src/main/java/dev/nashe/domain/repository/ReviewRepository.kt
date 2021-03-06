package dev.nashe.domain.repository

import dev.nashe.domain.model.review.Review

interface ReviewRepository {
    suspend fun createReview(review: Review)

    suspend fun getReviews(id  :  String) : List<Review>
}