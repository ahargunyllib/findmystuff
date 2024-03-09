package com.ahargunyllib.internraion.features.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Report(
    @SerialName("report_id")
    val reportId: Int? = null,
    val name: String = "",
    val note: String = "",
    @SerialName("image_url")
    val imageUrl: String = "",
    @SerialName("user_id")
    val userId: Int? = null,
    val fee: Double,
    val latitude: Double,
    val longitude: Double,
    @SerialName("created_at")
    val createdAt: String? = null
)
