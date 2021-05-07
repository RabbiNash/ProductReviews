package dev.nashe.productreviews.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dev.nashe.domain.repository.ProductPersistenceRepository

@HiltWorker
class ProductSyncWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters,
    private val repository: ProductPersistenceRepository

) : Worker(context, workerParameters) {

    override fun doWork(): Result {
        repository.syncRemoteProducts()

        return Result.success()
    }
}