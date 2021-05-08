package dev.nashe.data.api.helper

internal fun getJsonString(path: String): String {
    return ClassLoader.getSystemResource(path).readText()
}