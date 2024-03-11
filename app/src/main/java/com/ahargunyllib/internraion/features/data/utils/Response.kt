package com.ahargunyllib.internraion.features.data.utils

sealed class Response {
    data object Loading: Response()
    data class Success(val message: String): Response()
    data class Error(val message: String): Response()
}