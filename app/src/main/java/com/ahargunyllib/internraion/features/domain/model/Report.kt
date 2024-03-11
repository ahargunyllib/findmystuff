package com.ahargunyllib.internraion.features.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Report(
    @SerialName("report_id")
    val reportId: Int? = null,
    val name: String = "",
    val note: String = "",
    @SerialName("user_id")
    val userId: Int? = null,
    val fee: Double = 0.0,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    @SerialName("created_at")
    val createdAt: String? = null
)
