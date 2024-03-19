package com.ahargunyllib.internraion.features.presentation.screen.chat_list.chat_room

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahargunyllib.internraion.features.data.model.ChatRoomItemModelResponse
import com.ahargunyllib.internraion.features.data.network.SupabaseClient
import com.ahargunyllib.internraion.features.data.repository.chat_list.ChatListRepository
import com.ahargunyllib.internraion.features.data.utils.ChatRoomItemsResponse
import com.ahargunyllib.internraion.features.data.utils.MessagesResponse
import com.ahargunyllib.internraion.features.data.utils.Response
import com.ahargunyllib.internraion.features.data.utils.UserResponse
import com.ahargunyllib.internraion.features.domain.model.Message
import com.ahargunyllib.internraion.features.domain.model.User
import io.github.jan.supabase.realtime.PostgresAction
import io.github.jan.supabase.realtime.channel
import io.github.jan.supabase.realtime.postgresChangeFlow
import io.github.jan.supabase.realtime.postgresListDataFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class ChatRoomViewModel(
    private val chatListRepository: ChatListRepository,
    private val chatRoomId: String
) : ViewModel() {
    private val _userState = MutableStateFlow(User())
    val userState: StateFlow<User> = _userState.asStateFlow()

    private val _messagesState = MutableStateFlow(emptyList<Message>())
    val messagesState: StateFlow<List<Message>> = _messagesState.asStateFlow()

    var m = mutableStateOf("")

    init {
        getChatRoom(chatRoomId.toInt())
        getMessages(chatRoomId.toInt())
    }

    private fun getChatRoom(chatRoomId: Int) {
        viewModelScope.launch {
            chatListRepository.getOtherUser(chatRoomId).collect {
                when (it) {
                    is UserResponse.Success -> {
                        _userState.value = it.user
                    }

                    is UserResponse.Error -> {
                        Log.i("CHAT ROOM VIEW MODEL", "getChatRoom error: ${it.message}")
                    }

                    is UserResponse.Loading -> {
                        Log.i("CHAT ROOM VIEW MODEL", "getChatRoom: loading...")
                    }
                }
            }
        }
    }

    private fun getMessages(chatRoomId: Int) {
        viewModelScope.launch {
            chatListRepository.getMessages(chatRoomId).collect {
                when (it) {
                    is MessagesResponse.Error -> {
                        Log.i("CHAT ROOM VIEW MODEL", "getMessages error: ${it.message}")
                    }

                    is MessagesResponse.Loading -> {
                        Log.i("CHAT ROOM VIEW MODEL", "getMessages: loading...")
                    }

                    is MessagesResponse.Success -> {
                        _messagesState.value = it.data
                    }
                }

            }
        }
    }

    fun realtimeDB(scope: CoroutineScope) {
        viewModelScope.launch {
            try {
                val channel = SupabaseClient.client.channel("messages")
                val dataFlow = channel.postgresChangeFlow<PostgresAction>(schema = "public") {
                    table = "messages"
                }

                dataFlow.onEach {
                    when (it) {
                        is PostgresAction.Delete -> {
                            Log.i("CHAT ROOM VM", "getMessage: DELETED")
                        }

                        is PostgresAction.Insert -> {
                            val stringifiedData = it.record.toString()
                            val message = Json.decodeFromString<Message>(stringifiedData)

                            Log.i("CHAT ROOM VM", "getMessage: INSERTED ${it.record}")
                        }

                        is PostgresAction.Select -> {
                            Log.i("CHAT ROOM VM", "getMessage: SELECTED ${it.record}")
                        }

                        is PostgresAction.Update -> {
                            Log.i(
                                "CHAT ROOM VM",
                                "getMessage: UPDATED ${it.oldRecord} with ${it.record}"
                            )
                        }
                    }
                }.launchIn(scope)

                channel.subscribe()
            } catch (e: Exception) {
                Log.i("CHAT ROOM VM", "getMessage: ERROR ${e.message}")
            }
        }
    }

    fun sendMessage(){
        viewModelScope.launch {
            chatListRepository.sendMessage(chatRoomId.toInt(), m.value).collect {
                when (it) {
                    is Response.Error -> {
                        Log.i("CHAT ROOM VIEW MODEL", "sendMessage: error ${it.message}")
                    }
                    is Response.Loading -> {
                        Log.i("CHAT ROOM VIEW MODEL", "sendMessage: loading...")
                    }
                    is Response.Success -> {
                        Log.i("CHAT ROOM VIEW MODEL", "sendMessage: success ${it.message}")
                    }
                }
            }
        }
    }
}
