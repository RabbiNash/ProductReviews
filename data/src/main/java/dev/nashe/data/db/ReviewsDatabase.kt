package dev.nashe.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.nashe.data.entity.ProductEntity
import dev.nashe.data.entity.ReviewEntity

@Database(entities = [ProductEntity::class, ReviewEntity::class], version = 1)
abstract class ReviewsDatabase : RoomDatabase(){

    abstract fun productDao() : ProductDao

    abstract fun reviewsDao() : ReviewsDao
}