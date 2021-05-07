package dev.nashe.data.entity

import androidx.room.Ignore

open class BaseProduct(val reviews : List<ReviewEntity>? = null)