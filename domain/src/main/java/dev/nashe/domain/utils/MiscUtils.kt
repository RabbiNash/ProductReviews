package dev.nashe.domain.utils

fun getStringId() : String{
    val charPool : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
    return (1..10)
            .map {
                kotlin.random.Random.nextInt(0, charPool.size)
            }
            .map(charPool::get)
            .joinToString( "")
}