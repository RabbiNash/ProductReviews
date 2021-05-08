package dev.nashe.productreviews.ui.viewmodel

import android.app.Application
import androidx.lifecycle.*
import androidx.work.*
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.nashe.domain.interactors.reviews.CreateReview
import dev.nashe.domain.interactors.reviews.RetrieveReviews
import dev.nashe.productreviews.mapper.review.ReviewDomainMapper
import dev.nashe.productreviews.mapper.review.ReviewViewMapper
import dev.nashe.productreviews.model.ReviewView
import dev.nashe.productreviews.util.MathUtils.listAverage
import dev.nashe.productreviews.util.Result
import dev.nashe.productreviews.worker.ReviewSyncWorker
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject constructor(
    private val createReview: CreateReview,
    private val retrieveReviews: RetrieveReviews,
    private val domainMapper : ReviewDomainMapper,
    private val viewMapper: ReviewViewMapper
    ) :  ViewModel() {

    private val _reviewsLiveData = MutableLiveData<Result<List<ReviewView>>>()
    val reviewsLiveData: LiveData<Result<List<ReviewView>>>
        get() = _reviewsLiveData

    private val _reviewCreationSuccess = MutableLiveData<Result<Boolean>>()
    val reviewCreationLiveData : LiveData<Result<Boolean>>
        get() = _reviewCreationSuccess

    private val _reviewStarAvgLiveData = MutableLiveData<Double>()
    val reviewStarAvgLiveData : LiveData<Double>
        get() = _reviewStarAvgLiveData

    init {
        _reviewsLiveData.value = Result.Idle
    }

    fun createProductReview(reviewView: ReviewView){
        viewModelScope.launch {
            _reviewCreationSuccess.value = Result.Loading
            try {
                createReview(domainMapper.mapToDomain(reviewView))
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
                _reviewStarAvgLiveData.value = listAverage(reviewListing.map { review -> review.rating })
            } catch (e : Exception){
                _reviewsLiveData.value = Result.Error(e.message)
            }
        }
    }

}