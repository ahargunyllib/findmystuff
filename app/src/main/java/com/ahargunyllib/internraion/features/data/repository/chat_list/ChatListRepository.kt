package com.ahargunyllib.internraion.features.data.repository.chat_list

import com.ahargunyllib.internraion.features.data.model.ChatRoomItemModelResponse
import com.ahargunyllib.internraion.features.data.network.SupabaseClient
import com.ahargunyllib.internraion.features.data.utils.ChatRoomItemsResponse
import com.ahargunyllib.internraion.features.domain.model.ChatRoom
import com.ahargunyllib.internraion.features.domain.model.User
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ChatListRepository(
    private val supabaseClient: SupabaseClient
): IChatListRepository {
    override suspend fun getChatRooms(): Flow<ChatRoomItemsResponse> = flow {
        emit(ChatRoomItemsResponse.Loading)
        try {
            val chatRoomsItems: MutableList<ChatRoomItemModelResponse> = mutableListOf()

            val currentUserEmail = supabaseClient.client.auth.currentUserOrNull()?.email
            val currentUser = supabaseClient.client.postgrest.from("users").select {
                filter {
                    eq("email", currentUserEmail!!)
                }
            }.decodeSingle<User>()

            val chatRooms = supabaseClient.client.postgrest.from("chat_rooms").select {
                filter {
                    eq("founder_id", currentUser.userId!!)
                    eq("victim_id", currentUser.userId)
                    eq("isPayed", false)
                }
            }.decodeList<ChatRoom>()

            chatRooms.forEach {chatRoom ->
                var user = User()
                if (chatRoom.victimId == currentUser.userId){
                    user = supabaseClient.client.postgrest.from("users").select {
                        filter {
                            eq("user_id", chatRoom.founderId!!)
                        }
                    }.decodeSingle<User>()
                } else if (chatRoom.founderId == currentUser.userId){
                    user = supabaseClient.client.postgrest.from("users").select {
                        filter {
                            eq("user_id", chatRoom.victimId!!)
                        }
                    }.decodeSingle<User>()
                }

                val chatRoomItem = ChatRoomItemModelResponse(chatRoom, user)
                chatRoomsItems.add(chatRoomItem)
            }

            emit(ChatRoomItemsResponse.Success(chatRoomsItems))
        } catch (e: Exception) {
            emit(ChatRoomItemsResponse.Error(e.message ?: ""))
        }
    }.flowOn(Dispatchers.IO)
}