package dev.nashe.productreviews.ui.activities

import dagger.hilt.android.AndroidEntryPoint
import dev.nashe.productreviews.R
import dev.nashe.productreviews.databinding.ActivityMainBinding
import dev.nashe.productreviews.ui.activities.base.BaseActivity

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layout: Int
        get() = R.layout.activity_main
}