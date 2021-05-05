package dev.nashe.domain.interactors.base

import kotlinx.coroutines.flow.Flow

/**
 * For the use case that is going to coroutines flow
 *
 * */

abstract class FlowUseCase<T, in Params> {
    abstract suspend operator fun invoke(params: Params? = null): Flow<T>
}