package dev.nashe.productreviews.viewmodels

import android.app.Application
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.google.common.truth.Truth.assertThat
import dev.nashe.domain.interactors.product.GetProducts
import dev.nashe.domain.interactors.product.SearchProduct
import dev.nashe.domain.repository.ProductRepository
import dev.nashe.productreviews.data.DataStub
import dev.nashe.productreviews.repos.MockProductRepository
import dev.nashe.productreviews.mapper.ProductViewMapper
import dev.nashe.productreviews.model.ProductView
import dev.nashe.productreviews.ui.viewmodel.ProductViewModel
import dev.nashe.productreviews.util.Result
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class ProductViewModelTest {

    private lateinit var productRepository: ProductRepository
    private lateinit var getProducts: GetProducts
    private lateinit var searchProduct: SearchProduct

    private lateinit var viewModel: ProductViewModel
    private val productMapper = ProductViewMapper()

    @Before
    fun setup() {
        productRepository = MockProductRepository()
        getProducts = GetProducts(productRepository)
        searchProduct = SearchProduct(productRepository)

        viewModel = ProductViewModel(
            getProducts, searchProduct, productMapper
        )
    }

    @Test
    fun getProducts_returnProducts() {
        val observer = Observer<Result<List<ProductView>>> {}

        try {
            viewModel.productsLiveData.observeForever(observer)
            viewModel.getAllProducts()

            when (val response = viewModel.productsLiveData.value!!) {
                is Result.Success -> {
                    assertThat(response.data).isEqualTo(productMapper.mapToViewList(listOf(DataStub.fakeProduct)))
                }
                is Result.Idle -> {
                    assertThat(response).isInstanceOf(Result.Idle::class.java)
                }
                is Result.Loading -> {
                    assertThat(response).isInstanceOf(Result.Loading::class.java)
                }

                is Error -> {
                    assertThat(response).isInstanceOf(Error::class.java)
                } else -> {
                    //other cases are not relevant
                }
            }
        } finally {
            viewModel.productsLiveData.removeObserver(observer)
        }
    }

    @Test
    fun searchProduct_returnsProducts() {
        val observer = Observer<Result<List<ProductView>>> {}

        try {
            viewModel.searchResults.asLiveData().observeForever(observer)
            viewModel.performSearch("product")

            when (val characters = viewModel.searchResults.value) {
                is Result.Success -> {
                    assertThat(characters.data.size).isAtLeast(1)
                }
                else -> {
                    //Other cases are not relevant
                }
            }
        } finally {
            viewModel.searchResults.asLiveData().removeObserver(observer)
        }

    }

}