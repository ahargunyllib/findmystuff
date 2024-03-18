package com.ahargunyllib.internraion.features.data.repository.report_detail

import android.util.Log
import com.ahargunyllib.internraion.features.data.network.SupabaseClient
import com.ahargunyllib.internraion.features.data.utils.ReportResponse
import com.ahargunyllib.internraion.features.domain.model.Report
import com.ahargunyllib.internraion.features.domain.model.User
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.storage.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.time.Duration.Companion.minutes


class ReportDetailRepository(private val supabaseClient: SupabaseClient): IReportDetailRepository {
    override suspend fun getReport(reportId: Int): Flow<ReportResponse> = flow {
        emit(ReportResponse.Loading)
        try {
            val report = supabaseClient.client.postgrest.from("reports").select{
                filter {
                    eq("report_id", reportId)
                }
            }.decodeSingle<Report>()
            val url = supabaseClient.client.storage.from("images").publicUrl("${report.name}.jpg")
            val user = supabaseClient.client.postgrest.from("users").select{
                filter {
                    eq("user_id", report.userId!!)
                }
            }.decodeSingle<User>()
            Log.i("REPORT DETAIL REPOS", "getReport success: $report $user $url")
            emit(ReportResponse.Success(report, user, url))
        } catch (e: Exception) {
            emit(ReportResponse.Error(e.message ?: ""))
        }
    }.flowOn(Dispatchers.IO)
}