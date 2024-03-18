package com.ahargunyllib.internraion.features.data.repository.report_detail

import com.ahargunyllib.internraion.features.data.utils.ReportResponse
import kotlinx.coroutines.flow.Flow

interface IReportDetailRepository {
    suspend fun getReport(reportId: Int): Flow<ReportResponse>
}