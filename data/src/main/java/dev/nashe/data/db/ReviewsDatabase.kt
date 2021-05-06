package dev.nashe.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.nashe.data.entity.ProductEntity
import dev.nashe.data.entity.ReviewEntity

@Database(entities = [ProductEntity::class, ReviewEntity::class], version = 1)
abstract class ReviewsDatabase : RoomDatabase(){

    abstract fun productDao() : ProductDao

    abstract fun reviewsDao() : ReviewsDao

    companion object {
        @Volatile
        private var INSTANCE: ReviewsDatabase? = null

        fun getDatabase(appContext: Context): ReviewsDatabase {
            val tempInstance =
                    INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        appContext, ReviewsDatabase::class.java,
                        "reviewsdatabase"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}