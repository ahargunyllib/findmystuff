package com.ahargunyllib.internraion.features.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatRoom(
    @SerialName("chat_room_id")
    val chatRoomId: Int? = null,
    @SerialName("report_id")
    val reportId: Int? = null,
    @SerialName("founder_id")
    val founderId: Int? = null,
    @SerialName("victim_id")
    val victimId: Int? = null,
    @SerialName("recent_message_id")
    val recentMessageId: Int? = null,
    val isPayed: Boolean = false,
    @SerialName("created_at")
    val createdAt: String? = null
)
