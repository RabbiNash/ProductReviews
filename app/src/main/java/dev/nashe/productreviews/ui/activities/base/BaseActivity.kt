package dev.nashe.productreviews.ui.activities.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.work.*

abstract class BaseActivity<B : ViewDataBinding?> : AppCompatActivity() {

    private var wasFinishCalled = false

    private val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .setRequiresCharging(false)
        .build()

    var binding: B? = null
        protected set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layout)
    }

    fun enqueueWorker(workRequest: WorkRequest) {
        WorkManager.getInstance(application).enqueue(workRequest)
    }

    fun workRequestBuilder(workerClass: Class<out ListenableWorker?>) {
        val workRequest = OneTimeWorkRequest.Builder(workerClass).setConstraints(constraints)
            .build()
        enqueueWorker(workRequest)
    }


    @get:LayoutRes
    protected abstract val layout: Int
}