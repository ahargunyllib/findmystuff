package com.ahargunyllib.internraion.features.data.utils

import com.ahargunyllib.internraion.features.data.model.ReportItemModelResponse
import com.ahargunyllib.internraion.features.domain.model.Report
import com.ahargunyllib.internraion.features.domain.model.User

sealed class ReportItemResponse {
    data object Loading : ReportItemResponse()
    data class Success(val reportItems: List<ReportItemModelResponse>) : ReportItemResponse()
    data class Error(val message: String) : ReportItemResponse()
}