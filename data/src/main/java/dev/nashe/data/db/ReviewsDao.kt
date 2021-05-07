package dev.nashe.data.db

import androidx.room.*
import dev.nashe.data.entity.ProductEntity
import dev.nashe.data.entity.ReviewEntity

@Dao
interface ReviewsDao {

    @Insert
    suspend fun createReview(review : ReviewEntity) : Long

    @Query("SELECT * FROM reviews where synced = 0")
    fun findAllAsync() : List<ReviewEntity>

    @Query("SELECT * FROM reviews where productId = :productId")
    suspend fun getProductReview(productId : String) : List<ReviewEntity>

    @Update
    suspend fun update(obj: ReviewEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(obj: Collection<ReviewEntity>?): LongArray?

}