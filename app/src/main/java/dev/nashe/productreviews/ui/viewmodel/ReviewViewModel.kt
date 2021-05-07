package dev.nashe.productreviews.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.nashe.domain.interactors.reviews.CreateReview
import dev.nashe.productreviews.model.ProductView
import dev.nashe.productreviews.model.ReviewView
import dev.nashe.productreviews.util.Result
import javax.inject.Inject

class ReviewViewModel @Inject constructor(
    private val createReview: CreateReview
) :  ViewModel() {

    private val _reviewsLiveData = MutableLiveData<Result<List<ReviewView>>>()
    val reviewsLiveData: LiveData<Result<List<ReviewView>>>
        get() = _reviewsLiveData

    init {

    }
}