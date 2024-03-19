package com.ahargunyllib.internraion.features.data.repository.chat_list

import com.ahargunyllib.internraion.features.data.utils.ChatRoomItemsResponse
import com.ahargunyllib.internraion.features.data.utils.MessagesResponse
import com.ahargunyllib.internraion.features.data.utils.Response
import com.ahargunyllib.internraion.features.data.utils.UserResponse
import kotlinx.coroutines.flow.Flow

interface IChatListRepository {
    suspend fun getChatRooms(): Flow<ChatRoomItemsResponse>
    suspend fun getOtherUser(chatRoomId: Int): Flow<UserResponse>
    suspend fun getMessages(chatRoomId: Int): Flow<MessagesResponse>
    suspend fun sendMessage(chatRoomId: Int, m: String): Flow<Response>
}