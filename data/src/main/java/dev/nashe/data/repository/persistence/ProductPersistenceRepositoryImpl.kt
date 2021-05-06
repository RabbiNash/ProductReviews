package dev.nashe.data.repository.persistence

import android.util.Log
import dev.nashe.data.api.ApiService
import dev.nashe.data.db.ProductDao
import dev.nashe.data.mapper.products.ProductDomainMapper
import dev.nashe.data.mapper.products.ProductEntityMapper
import dev.nashe.data.repository.sync.SyncScope
import dev.nashe.data.repository.sync.product.ProductSynServiceImpl
import dev.nashe.domain.model.product.Product
import dev.nashe.domain.repository.ProductPersistenceRepository
import dev.nashe.domain.repository.sync.product.ProductSynCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductPersistenceRepositoryImpl @Inject constructor(
        private val apiService: ApiService,
        private val productDao: ProductDao,
        private val domainMapper: ProductDomainMapper,
        private val entityMapper : ProductEntityMapper
) : ProductPersistenceRepository, ProductSynCallback, CoroutineScope by SyncScope() {

    private val productSyncService =
            ProductSynServiceImpl(productSynCallback = this, apiService = this.apiService, entityMapper = entityMapper)

    override fun syncRemoteProducts() {
        productSyncService.syncProducts()
    }

    override fun getProductSyncSuccess(products: List<Product>) {
        launch (Dispatchers.IO) {
            productDao.insertAll(domainMapper.mapToEntityList(products))
        }
    }

    override fun getProductSyncFailure() {
        Log.d("ProductPersistence", "fail")
    }
}