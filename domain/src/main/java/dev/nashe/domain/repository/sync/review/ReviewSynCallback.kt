package dev.nashe.domain.repository.sync.review

import dev.nashe.domain.model.review.Review

interface ReviewSynCallback {
    fun getReviewSynSuccess(review: Review)
    fun getReviewSynFailure(review: Review)
}