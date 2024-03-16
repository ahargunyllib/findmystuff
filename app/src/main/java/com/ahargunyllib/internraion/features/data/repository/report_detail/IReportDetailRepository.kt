package com.ahargunyllib.internraion.features.data.repository.report_detail

import com.ahargunyllib.internraion.features.data.utils.ReportResponse
import com.ahargunyllib.internraion.features.domain.model.Report
import kotlinx.coroutines.flow.Flow

interface IReportDetailRepository {
    suspend fun getReport(reportId: Int): Flow<ReportResponse>

    suspend fun readFile(
        fileName: String,
        onImageUrlRetrieved: (url: String) -> Unit,
    )
}