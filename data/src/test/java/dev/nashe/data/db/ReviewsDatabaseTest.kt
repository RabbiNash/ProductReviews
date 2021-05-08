package dev.nashe.data.db


import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import dev.nashe.data.mapper.data.MockData
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ReviewsDatabaseTest {
    private lateinit var productDao: ProductDao
    private lateinit var reviewsDao: ReviewsDao
    private lateinit var db: ReviewsDatabase


    @Before
    fun setup() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, ReviewsDatabase::class.java)
            .allowMainThreadQueries().build()

        productDao = db.productDao()
        reviewsDao = db.reviewsDao()

        runBlocking {
            productDao.insertAll(listOf(MockData.fakeProduct))
            reviewsDao.insertAll(listOf(MockData.review))
        }
    }

    @Test
    fun `check if products are returned from products dao`() = runBlocking {
        assertThat(productDao.getProducts()).isNotEmpty()
    }

    @After
    fun tearDown() {
        db.close()
    }

}