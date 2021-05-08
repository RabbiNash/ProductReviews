package dev.nashe.productreviews.ui.activities

import androidx.work.*
import dagger.hilt.android.AndroidEntryPoint
import dev.nashe.productreviews.R
import dev.nashe.productreviews.databinding.ActivityMainBinding
import dev.nashe.productreviews.ui.activities.base.BaseActivity
import dev.nashe.productreviews.worker.ProductSyncWorker
import dev.nashe.productreviews.worker.ReviewSyncWorker

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layout: Int
        get() = R.layout.activity_main


    override fun onResume() {
        super.onResume()

        startSync()
    }

    private fun startSync(){
        startProductSync()
        startReviewSync()
    }

    private fun startProductSync() {
        workRequestBuilder(ProductSyncWorker::class.java)
    }

    private fun startReviewSync() {
        workRequestBuilder(ReviewSyncWorker::class.java)
    }
}