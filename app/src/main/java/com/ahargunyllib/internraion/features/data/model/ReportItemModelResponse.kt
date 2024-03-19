package com.ahargunyllib.internraion.features.data.model

import com.ahargunyllib.internraion.features.domain.model.Report
import com.ahargunyllib.internraion.features.domain.model.User

data class ReportItemModelResponse(
    val report: Report,
    val user: User,
    val imageUrl: String
)