package com.ahargunyllib.internraion.features.data.repository.report

import com.ahargunyllib.internraion.features.domain.model.Report
import io.github.jan.supabase.gotrue.user.UserSession

interface IReportRepository {
    suspend fun uploadFile(fileName: String, byteArray: ByteArray)
    suspend fun createReport(report: Report)
    suspend fun getCurrentUserId(): Int?
    suspend fun getReportId(reportName: String): Int?
}