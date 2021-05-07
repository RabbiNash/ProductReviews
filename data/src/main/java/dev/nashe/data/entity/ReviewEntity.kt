package dev.nashe.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "reviews")
data class ReviewEntity(
    @PrimaryKey val id : String,
    val locale: String,
    @ColumnInfo(name = "productId", index = true)
    val productId: String,
    val rating: Int,
    val text: String,
    var synced : Boolean = false
)