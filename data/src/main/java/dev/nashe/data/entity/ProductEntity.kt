package dev.nashe.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
        @PrimaryKey val id: String,
        val currency: String,
        val description: String,
        val imgUrl: String,
        val name: String,
        val price: Double
)