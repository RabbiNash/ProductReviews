package dev.nashe.productreviews.repos

import dev.nashe.domain.model.product.Product
import dev.nashe.domain.repository.ProductRepository
import dev.nashe.productreviews.data.DataStub
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MockProductRepository : ProductRepository {
    override suspend fun getProducts(): List<Product> = listOf(DataStub.fakeProduct)

    override suspend fun searchProduct(param: String): Flow<List<Product>> = flow {
        emit(listOf(DataStub.fakeProduct))
    }
}