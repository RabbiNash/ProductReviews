package dev.nashe.productreviews

import dev.nashe.productreviews.util.MathUtils
import org.junit.Test

import org.junit.Assert.*

/**
 * Test  Mathutil average
 */
class UtilTest {
    @Test
    fun listAverage_isCorrect() {
        assertTrue(MathUtils.listAverage(listOf(1,2,3,4)) == 2.5)
    }
}