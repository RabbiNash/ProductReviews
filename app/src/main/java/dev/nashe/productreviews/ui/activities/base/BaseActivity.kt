package dev.nashe.productreviews.ui.activities.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<B : ViewDataBinding?> : AppCompatActivity() {

    private var wasFinishCalled = false

    var binding: B? = null
        protected set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layout)
    }


    @get:LayoutRes
    protected abstract val layout: Int
}