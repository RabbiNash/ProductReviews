package dev.nashe.productreviews.ui.viewholders

import androidx.recyclerview.widget.RecyclerView
import dev.nashe.productreviews.databinding.ItemReviewBinding
import dev.nashe.productreviews.model.ReviewView
import dev.nashe.productreviews.ui.adapter.base.BaseRecyclerAdapter
import dev.nashe.productreviews.ui.viewholders.base.BaseViewHolder

class ReviewViewHolder(binding: ItemReviewBinding) :
    BaseViewHolder<ReviewView, ItemReviewBinding, BaseRecyclerAdapter.Callback<ReviewView>>(
        binding
    ) {

    override fun doOnBind(item: ReviewView, callback: BaseRecyclerAdapter.Callback<ReviewView>?) {
        binding.review = item

        val rootView = binding.root
        rootView.layoutParams = RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.MATCH_PARENT,
            RecyclerView.LayoutParams.WRAP_CONTENT
        )

        if (callback != null) {
            binding.root.setOnClickListener { callback.onItemSelected(item) }
        }
    }
}