package dev.nashe.productreviews.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.nashe.data.api.ApiService
import dev.nashe.data.api.RetrofitServiceFactory
import dev.nashe.data.api.ReviewApiService
import dev.nashe.data.db.ProductDao
import dev.nashe.data.db.ReviewsDao
import dev.nashe.data.db.ReviewsDatabase
import dev.nashe.data.repository.ProductRepositoryImpl
import dev.nashe.data.repository.ReviewsRepositoryImpl
import dev.nashe.data.repository.persistence.ProductPersistenceRepositoryImpl
import dev.nashe.data.repository.persistence.ReviewsPersistenceRepositoryImpl
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

        @Provides
        @Singleton
        fun providesReviewsDatabase(@ApplicationContext  context: Context) : ReviewsDatabase {
            return ReviewsDatabase.getDatabase(context)
        }

        @Singleton
        @Provides
        fun provideProductDao(appDatabase: ReviewsDatabase): ProductDao {
            return appDatabase.productDao()
        }

        @Singleton
        @Provides
        fun provideReviewsDao(appDatabase: ReviewsDatabase): ReviewsDao {
            return appDatabase.reviewsDao()
        }

        @Provides
        @Singleton
        fun providesProductRepository(productRepository: ProductRepositoryImpl): ProductRepository = productRepository

        @Provides
        @Singleton
        fun providesReviewRepository(reviewRepository: ReviewsRepositoryImpl): ReviewRepository = reviewRepository

        @Provides
        @Singleton
        fun providesProductPersistenceRepository(productPersistenceRepository: ProductPersistenceRepositoryImpl): ProductPersistenceRepository = productPersistenceRepository

        @Provides
        @Singleton
        fun providesReviewPersistenceRepository(reviewPersistenceRepository: ReviewsPersistenceRepositoryImpl): ReviewPersistenceRepository = reviewPersistenceRepository


    }
}