package dev.nashe.data.api

import dev.nashe.data.api.Constants.PRODUCTS_URL
import dev.nashe.data.api.helper.getJsonString
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class ServiceDispatcher : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path) {
            PRODUCTS_URL -> {
                MockResponse()
                        .setResponseCode(200)
                        .setBody(
                                getJsonString("mock/mock_products.json")
                        )
            }
            else -> throw IllegalArgumentException("${request.path} not found")
        }
    }
}