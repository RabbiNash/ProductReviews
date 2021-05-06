package dev.nashe.data.repository.sync.review

import dev.nashe.data.api.ReviewApiService
import dev.nashe.data.repository.sync.SyncScope
import dev.nashe.domain.model.review.Review
import dev.nashe.domain.repository.sync.review.ReviewSynCallback
import dev.nashe.domain.repository.sync.review.ReviewSyncService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class ReviewSyncServiceImpl @Inject constructor(
        private val reviewApiService: ReviewApiService,
        private val reviewSynCallback: ReviewSynCallback
        ) : ReviewSyncService, CoroutineScope by SyncScope() {

    override fun syncReview(review: Review) {
        launch(Dispatchers.IO) {
            try {
                reviewApiService.submitReview(review)
                reviewSynCallback.getReviewSynSuccess(review)
            } catch (e : Exception){
                reviewSynCallback.getReviewSynFailure(review)
            }
        }
    }
}