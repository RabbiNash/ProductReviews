package dev.nashe.data.db

import androidx.room.Dao
import androidx.room.Insert
import dev.nashe.data.entity.ReviewEntity

@Dao
interface ReviewsDao {

    @Insert
    suspend fun createReview(review : ReviewEntity) : Long

}