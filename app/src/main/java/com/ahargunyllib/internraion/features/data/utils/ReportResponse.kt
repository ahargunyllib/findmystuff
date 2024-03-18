package com.ahargunyllib.internraion.features.data.utils

import com.ahargunyllib.internraion.features.domain.model.Report
import com.ahargunyllib.internraion.features.domain.model.User

sealed class ReportResponse {
    data object Loading : ReportResponse()
    data class Success(val report: Report, val user: User, val imageUrl: String) : ReportResponse()
    data class Error(val message: String) : ReportResponse()
}