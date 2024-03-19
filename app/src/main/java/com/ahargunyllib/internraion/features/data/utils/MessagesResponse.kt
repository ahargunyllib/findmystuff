package com.ahargunyllib.internraion.features.data.utils

import com.ahargunyllib.internraion.features.domain.model.Message

sealed class MessagesResponse {
    data object Loading : MessagesResponse()
    data class Success(val data: List<Message>) : MessagesResponse()
    data class Error(val message: String) : MessagesResponse()
}