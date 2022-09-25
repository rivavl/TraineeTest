package com.marina.traineetest.domain.util

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null,
    val error: com.marina.traineetest.domain.util.Error? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(error: com.marina.traineetest.domain.util.Error) : Resource<T>(error = error)
    class Loading<T> : Resource<T>()
}

enum class Error {
    INTERNET_CONNECTION,
    UNKNOWN
}