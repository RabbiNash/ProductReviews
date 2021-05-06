package dev.nashe.data.mapper.base

interface RequestMapper<in I, out T> {

    fun mapToEntity(domain : I): T

    fun mapToEntityList(list: List<I>) : List<T> = list.mapTo(mutableListOf(), ::mapToEntity)
}