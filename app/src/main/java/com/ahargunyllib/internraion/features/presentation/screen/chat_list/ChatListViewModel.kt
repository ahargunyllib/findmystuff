package com.ahargunyllib.internraion.features.presentation.screen.chat_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahargunyllib.internraion.features.data.model.ChatRoomItemModelResponse
import com.ahargunyllib.internraion.features.data.repository.chat_list.ChatListRepository
import com.ahargunyllib.internraion.features.data.utils.ChatRoomItemsResponse
import com.ahargunyllib.internraion.features.domain.model.ChatRoom
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChatListViewModel(private val chatListRepository: ChatListRepository): ViewModel() {
    private val _chatRoomsState = MutableStateFlow(emptyList<ChatRoomItemModelResponse>())
    val chatRoomsState: StateFlow<List<ChatRoomItemModelResponse>> = _chatRoomsState.asStateFlow()

    init {
        getChatRooms()
    }

    private fun getChatRooms(){
        viewModelScope.launch {
            chatListRepository.getChatRooms().collect {
                when (it){
                    is ChatRoomItemsResponse.Success -> {
                        _chatRoomsState.value = it.chatRoomItems
                    }
                    is ChatRoomItemsResponse.Error -> {
                        Log.i("CHAT LIST VIEW MODEL", "getChatRooms error: ${it.message}")
                    }
                    is ChatRoomItemsResponse.Loading -> {
                        Log.i("CHAT LIST VIEW MODEL", "getChatRooms: loading...")
                    }
                }
            }
        }
    }
}