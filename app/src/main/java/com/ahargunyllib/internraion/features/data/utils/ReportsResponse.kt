package com.ahargunyllib.internraion.features.data.utils

import com.ahargunyllib.internraion.features.domain.model.Report

sealed class ReportsResponse {
    data object Loading : ReportsResponse()
    data class Success(val data: List<Report>) : ReportsResponse()
    data class Error(val message: String) : ReportsResponse()
}