package dev.nashe.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "reviews", foreignKeys = [ForeignKey(
        entity = ProductEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("productId")
    )]
)
data class ReviewEntity(
    val locale: String,
    val productId: String,
    val rating: Int,
    val text: String
)