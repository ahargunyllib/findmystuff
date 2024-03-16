package com.ahargunyllib.internraion.features.data.utils

import com.ahargunyllib.internraion.features.domain.model.Report

sealed class ReportResponse {
    data object Loading : ReportResponse()
    data class Success(val data: Report) : ReportResponse()
    data class Error(val message: String) : ReportResponse()
}