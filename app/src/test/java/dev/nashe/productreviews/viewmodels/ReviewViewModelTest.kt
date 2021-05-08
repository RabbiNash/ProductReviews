package dev.nashe.productreviews.viewmodels

import android.app.Application
import androidx.lifecycle.Observer
import com.google.common.truth.Truth
import dev.nashe.domain.interactors.reviews.CreateReview
import dev.nashe.domain.interactors.reviews.RetrieveReviews
import dev.nashe.domain.repository.ReviewRepository
import dev.nashe.productreviews.data.DataStub
import dev.nashe.productreviews.repos.MockReviewRepository
import dev.nashe.productreviews.mapper.review.ReviewDomainMapper
import dev.nashe.productreviews.mapper.review.ReviewViewMapper
import dev.nashe.productreviews.model.ReviewView
import dev.nashe.productreviews.ui.viewmodel.ReviewViewModel
import dev.nashe.productreviews.util.Result
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ReviewViewModelTest {
    private lateinit var reviewRepository: ReviewRepository
    private lateinit var retrieveReviews: RetrieveReviews
    private lateinit var createReview: CreateReview

    private lateinit var viewModel: ReviewViewModel
    private val reviewViewMapper = ReviewViewMapper()
    private val reviewDomainMapper = ReviewDomainMapper()

    @Before
    fun setup() {
        reviewRepository = MockReviewRepository()
        retrieveReviews = RetrieveReviews(reviewRepository)
        createReview = CreateReview(reviewRepository)

        viewModel = ReviewViewModel(
            createReview, retrieveReviews, reviewDomainMapper, reviewViewMapper
        )
    }

    @Test
    fun getProducts_returnProducts() {
        val observer = Observer<Result<List<ReviewView>>> {}

        try {
            viewModel.reviewsLiveData.observeForever(observer)
            viewModel.getProductReviews(DataStub.fakeProduct.id)

            when (val response = viewModel.reviewsLiveData.value!!) {
                is Result.Success -> {
                    Truth.assertThat(response.data).isEqualTo(reviewViewMapper.mapToViewList(listOf(DataStub.review)))
                }
                is Result.Idle -> {
                    Truth.assertThat(response).isInstanceOf(Result.Idle::class.java)
                }
                is Result.Loading -> {
                    Truth.assertThat(response).isInstanceOf(Result.Loading::class.java)
                }

                is Error -> {
                    Truth.assertThat(response).isInstanceOf(Error::class.java)
                }
                else -> {
                    //other cases are not relevant
                }
            }
        } finally {
            viewModel.reviewsLiveData.removeObserver(observer)
        }
    }
}