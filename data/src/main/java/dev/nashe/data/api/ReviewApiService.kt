package dev.nashe.data.api

import dev.nashe.data.entity.ReviewEntity
import dev.nashe.domain.model.review.Review
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface ReviewApiService {

    @POST("reviews/{productId}")
    suspend fun submitReview(@Path(value = "productId") productId : String, @Body review: Review): ReviewEntity

}