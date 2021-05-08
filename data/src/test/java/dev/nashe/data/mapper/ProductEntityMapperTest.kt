package dev.nashe.data.mapper

import com.google.common.truth.Truth.assertThat
import dev.nashe.data.mapper.data.MockData
import dev.nashe.data.mapper.products.ProductEntityMapper
import org.junit.Test

class ProductEntityMapperTest {
    private val mapper = ProductEntityMapper()

    @Test
    fun `checks that mapToDomain maps entity model to domain model`() {
        val fakeProductEntity = MockData.fakeProduct

        val domainProduct = mapper.mapToDomain(fakeProductEntity)

        assertThat(domainProduct.name).isEqualTo(fakeProductEntity.name)
        assertThat(domainProduct.currency).isEqualTo(fakeProductEntity.currency)
        assertThat(domainProduct.imgUrl).isEqualTo(fakeProductEntity.imgUrl)
        assertThat(domainProduct.id).isEqualTo(fakeProductEntity.id)
        assertThat(domainProduct.description).isEqualTo(fakeProductEntity.description)
    }
}