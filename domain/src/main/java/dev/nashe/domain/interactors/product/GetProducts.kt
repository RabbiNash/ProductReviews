package dev.nashe.domain.interactors.product

import dev.nashe.domain.repository.ProductRepository
import javax.inject.Inject

class GetProducts @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke() = repository.getProducts()
}