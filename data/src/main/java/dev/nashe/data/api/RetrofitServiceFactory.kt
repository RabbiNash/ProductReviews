package dev.nashe.data.api

import dev.nashe.data.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitServiceFactory {

    private const val PRODUCTS_BASE_URL = BuildConfig.PRIMARY_BASE_URL
    private const val REVIEWS_BASE_URL = BuildConfig.SECONDARY_BASE_URL

    fun makeProductsRetrofitApiService(isDebug: Boolean): ApiService {
        val retrofit =
            makeProductsRetrofitInstance(
                makeOkHttpClient(makeLoggingInterceptor(isDebug)), PRODUCTS_BASE_URL
            )
        return makeProductsApiService(retrofit)
    }

    fun makeReviewsRetrofitApiService(isDebug: Boolean) : ReviewApiService {
        val retrofit =
            makeReviewsRetrofitInstance(
                makeOkHttpClient(makeLoggingInterceptor(isDebug)), REVIEWS_BASE_URL
            )
        return makeReviewsApiService(retrofit)
    }

    private fun makeReviewsRetrofitInstance(okHttpClient: OkHttpClient, baseUrl: String) =
        makeRetrofitInstance(okHttpClient, baseUrl)

    private fun makeProductsApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    private fun makeReviewsApiService(retrofit: Retrofit): ReviewApiService {
        return retrofit.create(ReviewApiService::class.java)
    }

    private fun makeProductsRetrofitInstance(okHttpClient: OkHttpClient, baseUrl: String) =
        makeRetrofitInstance(okHttpClient, baseUrl)

    private fun makeRetrofitInstance(okHttpClient: OkHttpClient, baseUrl : String) =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private fun makeOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(90, TimeUnit.SECONDS)
            .readTimeout(90, TimeUnit.SECONDS)
            .build()
    }

    private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (isDebug) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return logging
    }
}