package dev.nashe.productreviews.ui.fragments.product

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.nashe.productreviews.R
import dev.nashe.productreviews.databinding.FragmentProductDescriptionBinding
import dev.nashe.productreviews.model.ReviewView
import dev.nashe.productreviews.ui.adapter.ReviewAdapter
import dev.nashe.productreviews.ui.adapter.base.BaseRecyclerAdapter
import dev.nashe.productreviews.ui.fragments.base.BaseFragment
import dev.nashe.productreviews.ui.viewmodel.ReviewViewModel
import dev.nashe.productreviews.util.ItemOffsetDecoration
import dev.nashe.productreviews.util.Result

@AndroidEntryPoint
class ProductDescriptionFragment : BaseFragment<FragmentProductDescriptionBinding>(), BaseRecyclerAdapter.Callback<ReviewView>, View.OnClickListener {
    override val layout: Int
        get() = R.layout.fragment_product_description

    private val args: ProductDescriptionFragmentArgs by navArgs()
    private val reviewViewModel : ReviewViewModel by navGraphViewModels(R.id.main_nav_graph){
        defaultViewModelProviderFactory
    }
    private val reviewAdapter : ReviewAdapter = ReviewAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            topAppBar.setNavigationOnClickListener {
                activity?.onBackPressed()
            }
            initReviewRecycler()

            product = args.product
        }

        binding?.listener = this

        reviewViewModel.getProductReviews(args.product.id)
        observeReviews()
        observeRatingAverage()
    }

    private fun observeReviews() {
        reviewViewModel.reviewsLiveData.observe(viewLifecycleOwner, Observer {
            when(it) {
                is Result.Success -> {
                    reviewAdapter.setItems(it.data)
                }
                is Result.Error -> {

                }
                is Result.Loading -> {

                }
                is Result.Idle -> {

                }
            }
        })
    }

    private fun observeRatingAverage(){
        reviewViewModel.reviewStarAvgLiveData.observe(viewLifecycleOwner, Observer {
            binding?.rbRating?.rating = it.toFloat()
        })
    }

    private fun initReviewRecycler(){
        binding!!.rvReviews.apply {
            adapter = reviewAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            addItemDecoration(ItemOffsetDecoration(context, R.dimen.padding_8))
        }
    }

    override fun onItemSelected(t: ReviewView?) {

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_add_review -> {
                navigate(ProductDescriptionFragmentDirections.actionProductDescriptionFragmentToAddReviewFragment(args.product.id))
            }
        }
    }
}