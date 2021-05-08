package dev.nashe.productreviews.data.mapper

import com.google.common.truth.Truth.assertThat
import dev.nashe.productreviews.data.DataStub
import dev.nashe.productreviews.mapper.ProductViewMapper
import org.junit.Test

class ProductViewMapperTest {

    private val mapper = ProductViewMapper()

    @Test
    fun `checks that mapToView maps domain model to view model`() {
        val mockProduct = DataStub.fakeProduct

        val productView = mapper.mapToView(mockProduct)

        assertThat(productView.name).isEqualTo(mockProduct.name)
        assertThat(productView.currency).isEqualTo(mockProduct.currency)
        assertThat(productView.id).isEqualTo(mockProduct.id)
        assertThat(productView.imgUrl).isEqualTo(mockProduct.imgUrl)
        assertThat(productView.description).isEqualTo(mockProduct.description)
    }

    @Test
    fun `checks that mapToViewList maps a domain model list to view model list`() {
        val mockProducts = listOf(DataStub.fakeProduct)

        val products = mapper.mapToViewList(mockProducts)

        assertThat(mockProducts.size).isEqualTo(products.size)
        assertThat(mockProducts[0].name).isEqualTo(products[0].name)
    }
}