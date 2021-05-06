package dev.nashe.data.repository

import dev.nashe.data.api.ApiService
import dev.nashe.data.db.ProductDao
import dev.nashe.data.mapper.products.ProductEntityMapper
import dev.nashe.domain.model.product.Product
import dev.nashe.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productDao: ProductDao,
    private val productEntityMapper: ProductEntityMapper
) : ProductRepository {

    override suspend fun getProducts(): List<Product> {
        return productEntityMapper.mapToDomainList(productDao.getProducts())
    }

    override suspend fun searchProduct(param: String): Flow<List<Product>> {
        return flow{
            emit(productEntityMapper.mapToDomainList(productDao.searchProduct(param)))
        }
    }
}