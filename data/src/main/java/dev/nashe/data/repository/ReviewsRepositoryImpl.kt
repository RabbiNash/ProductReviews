package dev.nashe.data.repository

import dev.nashe.data.db.ReviewsDao
import dev.nashe.data.mapper.reviews.ReviewDomainMapper
import dev.nashe.data.mapper.reviews.ReviewEntityMapper
import dev.nashe.domain.model.review.Review
import dev.nashe.domain.repository.ReviewRepository
import javax.inject.Inject

class ReviewsRepositoryImpl  @Inject constructor(
        private val reviewsDao: ReviewsDao,
        private val reviewDomainMapper: ReviewDomainMapper
) : ReviewRepository {

    override suspend fun createReview(review: Review) {
        reviewsDao.createReview(reviewDomainMapper.mapToEntity(review))
    }

}