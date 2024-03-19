package com.ahargunyllib.internraion.features.data.repository.chat_list

import com.ahargunyllib.internraion.features.data.model.ChatRoomItemModelResponse
import com.ahargunyllib.internraion.features.data.network.SupabaseClient
import com.ahargunyllib.internraion.features.data.utils.ChatRoomItemsResponse
import com.ahargunyllib.internraion.features.data.utils.MessagesResponse
import com.ahargunyllib.internraion.features.data.utils.Response
import com.ahargunyllib.internraion.features.data.utils.UserResponse
import com.ahargunyllib.internraion.features.domain.model.ChatRoom
import com.ahargunyllib.internraion.features.domain.model.Message
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

    override suspend fun getOtherUser(chatRoomId: Int): Flow<UserResponse> = flow {
        emit(UserResponse.Loading)
        try {
            val currentUserEmail = supabaseClient.client.auth.currentUserOrNull()?.email
            val currentUser = supabaseClient.client.postgrest.from("users").select {
                filter {
                    eq("email", currentUserEmail!!)
                }
            }.decodeSingle<User>()

            val chatRoom = supabaseClient.client.postgrest.from("chat_rooms").select {
                filter {
                    eq("chat_room_id", chatRoomId)
                }
            }.decodeSingle<ChatRoom>()

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

            emit(UserResponse.Success(user))
        } catch (e: Exception){
            emit(UserResponse.Error(e.message ?: ""))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getMessages(chatRoomId: Int): Flow<MessagesResponse> = flow<MessagesResponse> {
        emit(MessagesResponse.Loading)
        try {
            val messages = supabaseClient.client.postgrest.from("messages").select {
                filter {
                    eq("chat_room_id", chatRoomId)
                }
            }.decodeList<Message>()

            emit(MessagesResponse.Success(messages))
        } catch (e: Exception){
        emit(MessagesResponse.Error(e.message ?: ""))
    }
    }.flowOn(Dispatchers.IO)

    override suspend fun sendMessage(chatRoomId: Int, m: String): Flow<Response> = flow<Response> {
        emit(Response.Loading)
        try {
            val currentUserEmail = supabaseClient.client.auth.currentUserOrNull()?.email
            val currentUser = supabaseClient.client.postgrest.from("users").select {
                filter {
                    eq("email", currentUserEmail!!)
                }
            }.decodeSingle<User>()

            val message = Message(senderId = currentUser.userId, message = m, chatRoomId = chatRoomId)

            supabaseClient.client.postgrest.from("messages").insert(message)

            emit(Response.Success(m))
        } catch (e: Exception){
            emit(Response.Error(e.message ?: ""))
        }
    }.flowOn(Dispatchers.IO)
}