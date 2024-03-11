package com.ahargunyllib.internraion.features.data.repository.report

import com.ahargunyllib.internraion.features.domain.model.Report

interface IReportRepository {
    suspend fun uploadFile(fileName: String, byteArray: ByteArray)
    suspend fun createReport(report: Report)

}