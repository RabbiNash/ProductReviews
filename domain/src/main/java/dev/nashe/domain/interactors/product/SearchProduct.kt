package dev.nashe.domain.interactors.product

import dev.nashe.domain.interactors.base.FlowUseCase
import dev.nashe.domain.model.product.Product
import dev.nashe.domain.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SearchProduct @Inject constructor(
    private val repository: ProductRepository
) : FlowUseCase<List<Product>, String>() {

    override suspend operator fun invoke(params: String?): Flow<List<Product>> {
        if (params == null) throw IllegalArgumentException("Search parameter cannot be null")
        return repository.searchProduct(params).flowOn(Dispatchers.IO)
    }

}