package dev.nashe.productreviews.ui.fragments.review

import android.os.Bundle
import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import dev.nashe.productreviews.R
import dev.nashe.productreviews.databinding.FragmentAddReviewBinding
import dev.nashe.productreviews.ui.fragments.base.BaseFragment

@AndroidEntryPoint
class AddReviewFragment : BaseFragment<FragmentAddReviewBinding>() {
    override val layout: Int
        get() = R.layout.fragment_add_review

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            topAppBar.setNavigationOnClickListener {
                activity?.onBackPressed()
            }
        }
    }
}