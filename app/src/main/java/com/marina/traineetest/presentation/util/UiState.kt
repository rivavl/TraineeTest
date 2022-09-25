package com.marina.traineetest.presentation.util

sealed class UiState<T>(
    val data: T? = null,
    val message: String? = null,
    val error: ErrorOnUI? = null
) {
    class Success<T>(data: T) : UiState<T>(data)
    class Error<T>(error: ErrorOnUI) : UiState<T>(error = error)
    class Loading<T> : UiState<T>()
}

enum class ErrorOnUI {
    INTERNET_CONNECTION,
    UNKNOWN
}