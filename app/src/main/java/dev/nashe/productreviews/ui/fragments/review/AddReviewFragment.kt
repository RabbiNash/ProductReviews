package dev.nashe.productreviews.ui.fragments.review

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import dev.nashe.productreviews.R
import dev.nashe.productreviews.databinding.FragmentAddReviewBinding
import dev.nashe.productreviews.model.ReviewView
import dev.nashe.productreviews.ui.fragments.base.BaseFragment
import dev.nashe.productreviews.ui.viewmodel.ReviewViewModel
import dev.nashe.productreviews.util.Result

@AndroidEntryPoint
class AddReviewFragment : BaseFragment<FragmentAddReviewBinding>(), View.OnClickListener {
    override val layout: Int
        get() = R.layout.fragment_add_review

    private val reviewViewModel : ReviewViewModel by viewModels()
    private val args: AddReviewFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            topAppBar.setNavigationOnClickListener {
                activity?.onBackPressed()
            }
        }

        binding?.listener = this

        observeReviewCreationLiveData()
    }

    private fun observeReviewCreationLiveData(){
        reviewViewModel.reviewCreationLiveData.observe(viewLifecycleOwner, Observer { result ->
            when(result) {
                is Result.Success -> {
                    findNavController().navigateUp()
                    showToast("Review Saved Successfully")
                }
                is Result.Error -> {
                    showToast("An error occured")
                }
                is Result.Loading -> {

                }
                is Result.Idle -> {

                }
            }
        })
    }

    private fun submitReview(){
        binding?.apply {
            if (etMessage.text.isNullOrEmpty()){
                etMessage.error = "Required"
                return
            }
            reviewViewModel.createProductReview(ReviewView("en-Us", args.productId, rbRating.numStars, etMessage.text.toString()))
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT)
            .show()
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_submit_review -> {
                submitReview()
            }
        }
    }
}