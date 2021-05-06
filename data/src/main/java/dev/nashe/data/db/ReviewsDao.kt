package dev.nashe.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import dev.nashe.data.entity.ReviewEntity

@Dao
interface ReviewsDao {

    @Insert
    suspend fun createReview(review : ReviewEntity) : Long

    @Query("SELECT * FROM reviews where synced = 0")
    fun findAllAsync() : List<ReviewEntity>

    @Update
    suspend fun update(obj: ReviewEntity)

}