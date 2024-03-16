package com.ahargunyllib.internraion.features.data.repository.report_detail

import android.util.Log
import com.ahargunyllib.internraion.features.data.network.SupabaseClient
import com.ahargunyllib.internraion.features.data.utils.ReportResponse
import com.ahargunyllib.internraion.features.data.utils.Response
import com.ahargunyllib.internraion.features.domain.model.Report
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.storage.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
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
            Log.i("REPORT DETAIL REPOS", "getReport success: $report")
            emit(ReportResponse.Success(report))
        } catch (e: Exception) {
            emit(ReportResponse.Error(e.message ?: ""))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun readFile(
        fileName: String,
        onImageUrlRetrieved: (url: String) -> Unit,
    ) {
        val url = supabaseClient.client.storage.from("images").createSignedUrl("$fileName.jpg", 60.minutes)
        onImageUrlRetrieved(url)
    }
}