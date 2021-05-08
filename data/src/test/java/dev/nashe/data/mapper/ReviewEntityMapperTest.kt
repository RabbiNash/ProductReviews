package dev.nashe.data.mapper

import com.google.common.truth.Truth
import dev.nashe.data.mapper.data.MockData
import dev.nashe.data.mapper.products.ProductEntityMapper
import dev.nashe.data.mapper.reviews.ReviewEntityMapper
import org.junit.Test

class ReviewEntityMapperTest {
    private val mapper = ReviewEntityMapper()

    @Test
    fun `checks that mapToDomain maps entity model to domain model`() {
        val fakeReviewEntity = MockData.review

        val domainReview = mapper.mapToDomain(fakeReviewEntity)

        Truth.assertThat(domainReview.productId).isEqualTo(fakeReviewEntity.productId)
        Truth.assertThat(domainReview.text).isEqualTo(fakeReviewEntity.text)
        Truth.assertThat(domainReview.rating).isEqualTo(fakeReviewEntity.rating)
        Truth.assertThat(domainReview.locale).isEqualTo(fakeReviewEntity.locale)
    }
}