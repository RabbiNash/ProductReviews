package dev.nashe.productreviews.ui.fragments.product

import android.os.Bundle
import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import dev.nashe.productreviews.R
import dev.nashe.productreviews.databinding.FragmentDashboardBinding
import dev.nashe.productreviews.ui.fragments.base.BaseFragment

@AndroidEntryPoint
class DashboardFragment : BaseFragment<FragmentDashboardBinding>() {
    override val layout: Int
        get() = R.layout.fragment_dashboard

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}