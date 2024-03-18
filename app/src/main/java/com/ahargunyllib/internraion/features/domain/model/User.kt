package com.ahargunyllib.internraion.features.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("user_id")
    val userId: Int? = null,
    @SerialName("full_name")
    val fullName: String = "",
    val email: String = "",
    @SerialName("created_at")
    val createdAt: String? = null
)
