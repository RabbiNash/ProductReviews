package dev.nashe.productreviews.ui.fragments.product

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.nashe.productreviews.R
import dev.nashe.productreviews.databinding.FragmentDashboardBinding
import dev.nashe.productreviews.model.ProductView
import dev.nashe.productreviews.ui.adapter.ProductAdapter
import dev.nashe.productreviews.ui.adapter.base.BaseRecyclerAdapter
import dev.nashe.productreviews.ui.fragments.base.BaseFragment
import dev.nashe.productreviews.ui.viewmodel.ProductViewModel
import dev.nashe.productreviews.util.ItemOffsetDecoration
import dev.nashe.productreviews.util.Result

@AndroidEntryPoint
class DashboardFragment : BaseFragment<FragmentDashboardBinding>(), BaseRecyclerAdapter.Callback<ProductView> {

    override val layout: Int
        get() = R.layout.fragment_dashboard

    private val viewModel : ProductViewModel by viewModels()

    private val productAdapter : ProductAdapter =  ProductAdapter(this)

    private val textWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
            viewModel.performSearch(text.toString())
        }

        override fun afterTextChanged(p0: Editable?) {}
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            etSearch.addTextChangedListener(textWatcher)
            initDashboardView()
        }

        observeProductsLiveData()
        observeSearchLiveData()
    }

    private fun observeSearchLiveData() {
        viewModel.searchResults.asLiveData()
            .observe(viewLifecycleOwner, Observer {
                when(it) {
                    is Result.Idle -> {

                    }
                    is Result.Loading -> {

                    }
                    is Result.Error -> {

                    }
                    is Result.Success -> {
                        productAdapter.setItems(it.data)
                    }
                }
            })
    }

    private fun observeProductsLiveData() {
        viewModel.productsLiveData.observe(viewLifecycleOwner, Observer {
            when(it) {
                is Result.Idle -> {

                }
                is Result.Loading -> {

                }
                is Result.Error -> {

                }
                is Result.Success -> {
                    productAdapter.setItems(it.data)
                }
            }
        })
    }

    private fun initDashboardView(){
        binding!!.rvProducts.apply {
            adapter = productAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            addItemDecoration(ItemOffsetDecoration(context, R.dimen.padding_8))
        }
    }

    override fun onItemSelected(t: ProductView) {
        navigate(DashboardFragmentDirections.actionDashboardFragmentToProductDescriptionFragment(t))
    }
}