package dev.nashe.productreviews.ui.viewholders

import androidx.recyclerview.widget.RecyclerView
import dev.nashe.productreviews.databinding.ItemProductBinding
import dev.nashe.productreviews.model.ProductView
import dev.nashe.productreviews.ui.adapter.base.BaseRecyclerAdapter
import dev.nashe.productreviews.ui.viewholders.base.BaseViewHolder

class ProductViewHolder(binding: ItemProductBinding) :
    BaseViewHolder<ProductView, ItemProductBinding, BaseRecyclerAdapter.Callback<ProductView>>(
        binding
    ) {

    override fun doOnBind(item: ProductView, callback: BaseRecyclerAdapter.Callback<ProductView>?) {
        binding.item = item

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