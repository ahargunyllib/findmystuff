package com.ahargunyllib.internraion.features.data.model

import com.ahargunyllib.internraion.features.domain.model.ChatRoom
import com.ahargunyllib.internraion.features.domain.model.Report
import com.ahargunyllib.internraion.features.domain.model.User

data class ChatRoomItemModelResponse(
    val chatRoom: ChatRoom,
    val user: User,
)