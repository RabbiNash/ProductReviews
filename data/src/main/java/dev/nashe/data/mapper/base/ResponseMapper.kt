package dev.nashe.data.mapper.base

interface ResponseMapper<in I, out T> {

    fun mapToDomain(entity : I): T

    fun mapToDomainList(list: List<I>) : List<T> = list.mapTo(mutableListOf(), ::mapToDomain)
}