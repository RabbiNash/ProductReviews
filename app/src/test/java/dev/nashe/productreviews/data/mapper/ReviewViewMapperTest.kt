package dev.nashe.productreviews.data.mapper

import com.google.common.truth.Truth
import dev.nashe.productreviews.data.DataStub
import dev.nashe.productreviews.mapper.review.ReviewViewMapper
import org.junit.Test

class ReviewViewMapperTest {
    private val mapper = ReviewViewMapper()

    @Test
    fun `checks that mapToView maps domain model to view model`() {
        val mockReview = DataStub.review

        val reviewView = mapper.mapToView(mockReview)

        Truth.assertThat(reviewView.locale).isEqualTo(mockReview.locale)
        Truth.assertThat(reviewView.productId).isEqualTo(mockReview.productId)
        Truth.assertThat(reviewView.rating).isEqualTo(mockReview.rating)
        Truth.assertThat(reviewView.text).isEqualTo(mockReview.text)

    }

    @Test
    fun `checks that mapToViewList maps a domain model list to view model list`() {
        val mockReviews = listOf(DataStub.review)

        val reviews = mapper.mapToViewList(mockReviews)

        Truth.assertThat(mockReviews.size).isEqualTo(reviews.size)
        Truth.assertThat(mockReviews[0].productId).isEqualTo(mockReviews[0].productId)
    }
}