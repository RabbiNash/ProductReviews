package dev.nashe.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "reviews", foreignKeys = [ForeignKey(
        entity = ProductEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("productId")
    )]
)
data class ReviewEntity(
    @PrimaryKey val id : String,
    val locale: String,
    val productId: String,
    val rating: Int,
    val text: String,
    var synced : Boolean = false
)