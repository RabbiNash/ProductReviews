package dev.nashe.domain.repository.sync.review

import dev.nashe.domain.model.review.Review

interface ReviewSyncService {
    fun syncReview(review : Review)
}