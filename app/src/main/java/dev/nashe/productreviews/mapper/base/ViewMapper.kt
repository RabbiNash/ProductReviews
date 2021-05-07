package dev.nashe.productreviews.mapper.base

interface ViewMapper<out V, in D> {
    fun mapToView(domain: D): V

    fun mapToViewList(list: List<D>): List<V> = list.mapTo(mutableListOf(), ::mapToView)
}