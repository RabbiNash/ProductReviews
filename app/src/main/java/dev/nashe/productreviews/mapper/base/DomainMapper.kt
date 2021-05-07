package dev.nashe.productreviews.mapper.base

interface DomainMapper<out D, in V> {
    fun mapToDomain(view: V): D
}