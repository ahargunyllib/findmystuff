package com.ahargunyllib.internraion.features.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Message(
    @SerialName("message_id")
    val messageId: Int? = null,
    @SerialName("sender_id")
    val senderId: Int? = null,
    val message: String? = null,
    @SerialName("chat_room_id")
    val chatRoomId: Int? = null,
    @SerialName("created_at")
    val createdAt: String? = null
)
