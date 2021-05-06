package dev.nashe.productreviews.ui.viewholders.base

import android.content.Context
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import dev.nashe.productreviews.ui.adapter.base.BaseRecyclerAdapter

abstract class BaseViewHolder<T, B : ViewDataBinding?, C : BaseRecyclerAdapter.Callback<T>?>(
    val binding: B
) :
    RecyclerView.ViewHolder(binding!!.root) {
    val context: Context = binding!!.root.context
    var item: T? = null
        private set

    @JvmOverloads
    fun bind(item: T, callback: C? = null) {
        this.item = item
        doOnBind(item, callback)
        binding!!.executePendingBindings()
    }

    protected abstract fun doOnBind(item: T, callback: C?)

}
