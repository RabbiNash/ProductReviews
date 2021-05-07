package dev.nashe.productreviews.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import dev.nashe.productreviews.databinding.ItemProductBinding
import dev.nashe.productreviews.model.ProductView
import dev.nashe.productreviews.ui.adapter.base.BaseRecyclerAdapter
import dev.nashe.productreviews.ui.viewholders.ProductViewHolder

class ProductAdapter(callback: Callback<ProductView>) :
    BaseRecyclerAdapter<ProductView, ProductViewHolder>(callback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(ItemProductBinding.inflate(LayoutInflater.from(parent.context)))
    }
}