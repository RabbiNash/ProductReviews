package dev.nashe.domain.repository

import dev.nashe.domain.model.review.Review

interface ReviewPersistenceRepository {
    fun syncReview(review: Review)
    fun insertReview(review: Review)
}