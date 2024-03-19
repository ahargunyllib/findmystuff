package com.ahargunyllib.internraion.features.data.utils

import com.ahargunyllib.internraion.features.data.model.ChatRoomItemModelResponse

sealed class ChatRoomItemsResponse {
    data object Loading : ChatRoomItemsResponse()
    data class Success(val chatRoomItems: List<ChatRoomItemModelResponse>) : ChatRoomItemsResponse()
    data class Error(val message: String) : ChatRoomItemsResponse()
}