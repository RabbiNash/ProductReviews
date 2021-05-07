package dev.nashe.productreviews.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.nashe.domain.interactors.product.GetProducts
import dev.nashe.domain.interactors.product.SearchProduct
import dev.nashe.productreviews.mapper.ProductViewMapper
import dev.nashe.productreviews.model.ProductView
import dev.nashe.productreviews.util.Result
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductViewModel @Inject constructor(
    private val getProducts: GetProducts,
    private val searchProduct: SearchProduct,
    private val productViewMapper: ProductViewMapper
) : ViewModel() {

    private val _productsLiveData = MutableLiveData<Result<List<ProductView>>>()
    val productsLiveData: LiveData<Result<List<ProductView>>>
        get() = _productsLiveData

    private val _searchResults = MutableStateFlow<Result<List<ProductView>>>(Result.Idle)
    val searchResults: StateFlow<Result<List<ProductView>>>
        get() = _searchResults

    private val searchInput = MutableStateFlow("")

    init {
        _productsLiveData.value = Result.Idle

        viewModelScope.launch {
            //Debounce the input to reduce unnecessary requests to the database
            // and to also wait and confirm if the user has actually put some input

            searchInput.debounce(timeoutMillis = DEBOUNCE_VAL).collect {
                if (it.isEmpty()) {
                    _searchResults.value = Result.Idle
                } else {
                    searchProduct(it)
                        .catch { throwable ->
                                _searchResults.value = Result.Error(throwable.message)
                        }
                        .collect { productListing ->
                            _searchResults.value =
                                Result.Success(productViewMapper.mapToViewList(productListing))
                        }
                }
            }
        }
    }

    fun getAllProducts() {
        viewModelScope.launch {
            _productsLiveData.value = Result.Loading
            try {
                val productListing = productViewMapper.mapToViewList(getProducts())
                _productsLiveData.value = Result.Success(productListing)
            } catch (e: Exception) {
                _productsLiveData.value = Result.Error(e.message)
            }
        }
    }

    companion object {
        const val DEBOUNCE_VAL = 500L
    }
}