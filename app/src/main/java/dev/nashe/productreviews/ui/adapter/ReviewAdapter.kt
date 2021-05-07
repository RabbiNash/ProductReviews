package dev.nashe.productreviews.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import dev.nashe.productreviews.databinding.ItemReviewBinding
import dev.nashe.productreviews.model.ReviewView
import dev.nashe.productreviews.ui.adapter.base.BaseRecyclerAdapter
import dev.nashe.productreviews.ui.viewholders.ReviewViewHolder

class ReviewAdapter(callback: Callback<ReviewView>) :
    BaseRecyclerAdapter<ReviewView, ReviewViewHolder>(callback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        return ReviewViewHolder(ItemReviewBinding.inflate(LayoutInflater.from(parent.context)))
    }
}