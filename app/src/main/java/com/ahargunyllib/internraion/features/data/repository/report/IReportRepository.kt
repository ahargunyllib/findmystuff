package com.ahargunyllib.internraion.features.data.repository.report

import com.ahargunyllib.internraion.features.domain.model.Report

interface IReportRepository {
    suspend fun uploadFile(fileName: String, byteArray: ByteArray)
    suspend fun readFile(
        fileName: String,
        onImageUrlRetrieved: (url: String) -> Unit,
    )
    suspend fun createReport(report: Report)

}