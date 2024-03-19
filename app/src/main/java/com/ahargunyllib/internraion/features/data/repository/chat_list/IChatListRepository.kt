package com.ahargunyllib.internraion.features.data.repository.chat_list

import com.ahargunyllib.internraion.features.data.utils.ChatRoomItemsResponse
import kotlinx.coroutines.flow.Flow

interface IChatListRepository {
    suspend fun getChatRooms(): Flow<ChatRoomItemsResponse>
}