package dev.nashe.data.repository.sync.product

import dev.nashe.data.api.ApiService
import dev.nashe.data.mapper.products.ProductEntityMapper
import dev.nashe.data.repository.sync.SyncScope
import dev.nashe.domain.repository.sync.product.ProductSynCallback
import dev.nashe.domain.repository.sync.product.ProductSyncService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class ProductSynServiceImpl @Inject constructor(
        private val apiService: ApiService,
        private val productSynCallback: ProductSynCallback,
        private val entityMapper: ProductEntityMapper
): ProductSyncService, CoroutineScope by SyncScope() {

    override fun syncProducts() {
        launch(Dispatchers.IO) {
            try {
                productSynCallback.getProductSyncSuccess(apiService.getProducts())
            } catch (e : Exception){
                productSynCallback.getProductSyncFailure()
            }
        }

    }
}