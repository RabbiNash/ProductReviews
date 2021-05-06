package dev.nashe.productreviews.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dev.nashe.domain.repository.ReviewPersistenceRepository

@HiltWorker
class ReviewSyncWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters,
    private val repository: ReviewPersistenceRepository

) : Worker(context, workerParameters) {

    override fun doWork(): Result {

        val allSync = repository.getAllAsyncReviews()

        allSync.forEach { review ->
            repository.syncReview(review)
        }

        return Result.success()
    }

}