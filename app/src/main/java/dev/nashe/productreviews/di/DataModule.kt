package dev.nashe.productreviews.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.nashe.data.api.ApiService
import dev.nashe.data.api.RetrofitServiceFactory
import dev.nashe.data.api.ReviewApiService
import dev.nashe.data.repository.ProductRepositoryImpl
import dev.nashe.data.repository.ReviewsRepositoryImpl
import dev.nashe.data.repository.persistence.ProductPersistenceRepositoryImpl
import dev.nashe.domain.repository.ProductPersistenceRepository
import dev.nashe.domain.repository.ProductRepository
import dev.nashe.domain.repository.ReviewPersistenceRepository
import dev.nashe.domain.repository.ReviewRepository
import dev.nashe.productreviews.BuildConfig
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    companion object {
        @Provides
        @Singleton
        fun providesApiService(): ApiService {
            return RetrofitServiceFactory.makeProductsRetrofitApiService(BuildConfig.DEBUG)
        }

        @Provides
        @Singleton
        fun providesReviewsApiService() : ReviewApiService {
            return RetrofitServiceFactory.makeReviewsRetrofitApiService(BuildConfig.DEBUG)
        }
    }

    @Binds
    @Singleton
    abstract fun bindsProductRepository(productRepository: ProductRepositoryImpl): ProductRepository

    @Binds
    @Singleton
    abstract fun bindsReviewsRepository(reviewRepository: ReviewsRepositoryImpl): ReviewRepository

    @Binds
    @Singleton
    abstract fun bindsProductPersistenceRepository(productPersistenceRepository: ProductPersistenceRepositoryImpl): ProductPersistenceRepository

    @Binds
    @Singleton
    abstract fun bindsReviewsPersistenceRepository(reviewPersistenceRepository: ReviewPersistenceRepository): ReviewPersistenceRepository


}