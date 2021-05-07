package dev.nashe.productreviews.ui.viewmodel

import android.app.Application
import androidx.lifecycle.*
import androidx.work.*
import dev.nashe.domain.interactors.reviews.CreateReview
import dev.nashe.domain.interactors.reviews.RetrieveReviews
import dev.nashe.productreviews.mapper.review.ReviewDomainMapper
import dev.nashe.productreviews.mapper.review.ReviewViewMapper
import dev.nashe.productreviews.model.ReviewView
import dev.nashe.productreviews.util.Result
import dev.nashe.productreviews.worker.ReviewSyncWorker
import kotlinx.coroutines.launch
import javax.inject.Inject

class ReviewViewModel @Inject constructor(
    private val createReview: CreateReview,
    private val retrieveReviews: RetrieveReviews,
    private val domainMapper : ReviewDomainMapper,
    private val viewMapper: ReviewViewMapper,
    application: Application
) :  AndroidViewModel(application) {

    private val _reviewsLiveData = MutableLiveData<Result<List<ReviewView>>>()
    val reviewsLiveData: LiveData<Result<List<ReviewView>>>
        get() = _reviewsLiveData

    private val _reviewCreationSuccess = MutableLiveData<Result<Boolean>>()
    val reviewCreationLiveData : LiveData<Result<Boolean>>
        get() = _reviewCreationSuccess

    init {
        _reviewsLiveData.value = Result.Idle
    }

    fun createProductReview(reviewView: ReviewView){
        viewModelScope.launch {
            _reviewCreationSuccess.value = Result.Loading
            try {
                createReview(domainMapper.mapToDomain(reviewView))
                startSync()
            } catch (e: Exception) {
                _reviewCreationSuccess.value = Result.Error(e.message)
            }
        }
    }

    fun getProductReviews(id : String){
        viewModelScope.launch {
            _reviewsLiveData.value = Result.Loading
            try {
                val reviewListing  =  viewMapper.mapToViewList(retrieveReviews(id))
                _reviewsLiveData.value = Result.Success(reviewListing)
            } catch (e : Exception){
                _reviewsLiveData.value = Result.Error(e.message)
            }
        }
    }

    private fun startSync() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresCharging(false)
            .build()

        val reviewSyncWorker =
            OneTimeWorkRequest.Builder(ReviewSyncWorker::class.java).setConstraints(constraints)
                .build()

        WorkManager.getInstance(getApplication()).enqueue(reviewSyncWorker)
    }
}