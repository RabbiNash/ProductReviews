package dev.nashe.productreviews.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductView(
    val currency: String,
    val description: String,
    val id: String,
    val imgUrl: String,
    val name: String,
    val price: Double
) : Parcelable