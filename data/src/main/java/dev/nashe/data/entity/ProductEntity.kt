package dev.nashe.data.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import dev.nashe.domain.model.review.Review

@Entity(tableName = "products")
data class ProductEntity(
        @PrimaryKey val id: String,
        val currency: String,
        val description: String,
        val imgUrl: String,
        val name: String,
        val price: Int,
        @Ignore val reviews: List<Review>
)