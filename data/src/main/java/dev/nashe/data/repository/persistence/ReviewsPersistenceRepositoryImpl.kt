package dev.nashe.data.repository.persistence

import android.util.Log
import dev.nashe.data.api.ReviewApiService
import dev.nashe.data.db.ReviewsDao
import dev.nashe.data.mapper.reviews.ReviewDomainMapper
import dev.nashe.data.repository.sync.SyncScope
import dev.nashe.data.repository.sync.review.ReviewSyncServiceImpl
import dev.nashe.domain.model.review.Review
import dev.nashe.domain.repository.ReviewPersistenceRepository
import dev.nashe.domain.repository.sync.review.ReviewSynCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ReviewsPersistenceRepositoryImpl @Inject constructor(
        private val reviewApiService: ReviewApiService,
        private val reviewsDao: ReviewsDao,
        private val domainMapper: ReviewDomainMapper
) : ReviewPersistenceRepository, ReviewSynCallback, CoroutineScope by SyncScope() {

    private val reviewSyncService =
            ReviewSyncServiceImpl(reviewSynCallback = this, reviewApiService = this.reviewApiService)

    override fun syncReview(review: Review) {
       reviewSyncService.syncReview(review)
    }

    override fun insertReview(review: Review) {
        launch(Dispatchers.IO){
            reviewsDao.createReview(domainMapper.mapToEntity(review))
        }
    }

    override fun getReviewSynSuccess(review: Review) {
        Log.d("SyncService", "Review Sync Success")
        //todo implement synced flag
    }

    override fun getReviewSynFailure(review: Review) {
        Log.d("SyncService", "Review Sync Failure")
    }
}