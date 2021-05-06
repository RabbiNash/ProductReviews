package dev.nashe.domain

import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.whenever
import dev.nashe.domain.interactors.product.GetProducts
import dev.nashe.domain.interactors.product.SearchProduct
import dev.nashe.domain.interactors.reviews.CreateReview
import dev.nashe.domain.model.product.Product
import dev.nashe.domain.model.review.Review
import dev.nashe.domain.repository.ProductRepository
import dev.nashe.domain.repository.ReviewRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.lang.IllegalArgumentException

@RunWith(MockitoJUnitRunner::class)
class InteractorTest {

    private lateinit var getProducts: GetProducts
    private lateinit var createReview : CreateReview
    private lateinit var searchProduct: SearchProduct

    @Mock
    lateinit var productRepository : ProductRepository

    @Mock
    lateinit var reviewRepository: ReviewRepository

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        getProducts = GetProducts(productRepository)
        searchProduct = SearchProduct(productRepository)
        createReview = CreateReview(reviewRepository)
    }

    private suspend fun stubProductRepositoryGetProducts(product : Product){
        whenever(getProducts()).thenReturn(listOf(product))
    }

    private suspend fun stubReviewRepositoryCreateReview(review : Review){
        whenever(createReview(review)).thenReturn(Unit)
    }

    private suspend fun stubProductRepositorySearchProductByName(result : Flow<List<Product>>){
        whenever(searchProduct(DataStub.productName)).thenReturn(result)
    }

    @Test
    fun `check if products are retrieved from the repository`() = runBlockingTest {
        stubProductRepositoryGetProducts(DataStub.fakeProduct)

        val products = getProducts()

        assertThat(products).isEqualTo(listOf(DataStub.fakeProduct))
    }

    @Test
    fun `check if review is created by unit return`() = runBlockingTest {
        stubReviewRepositoryCreateReview(DataStub.review)

        val unitRes = createReview(DataStub.review)

        assertThat(unitRes).isEqualTo(Unit)
    }

//    @Test
//    fun `check if product search retrieves a product if its available`() = runBlockingTest {
//        stubProductRepositorySearchProductByName(flowOf(listOf(DataStub.fakeProduct)))
//
//        val product = searchProduct(DataStub.productName).first()
//
//        assertThat(product.size).isAtLeast(MIN_FLOW_VAL)
//    }

    @Test(expected = IllegalArgumentException::class)
    fun `check if illegal argument exception is raised if search param is null`() = runBlockingTest {
        searchProduct().first()
    }

    companion object {
        const val MIN_FLOW_VAL = 0
    }

}