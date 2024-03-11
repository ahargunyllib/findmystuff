package com.ahargunyllib.internraion.features.data.repository.report_detail

import com.ahargunyllib.internraion.features.domain.model.Report

interface IReportDetailRepository {
    suspend fun getReport(reportId: Int): Report

    suspend fun readFile(
        fileName: String,
        onImageUrlRetrieved: (url: String) -> Unit,
    )
}