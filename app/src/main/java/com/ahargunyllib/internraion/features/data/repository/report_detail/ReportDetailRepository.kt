package com.ahargunyllib.internraion.features.data.repository.report_detail

import com.ahargunyllib.internraion.features.data.network.SupabaseClient
import com.ahargunyllib.internraion.features.domain.model.Report
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.storage.storage
import kotlin.time.Duration.Companion.minutes


class ReportDetailRepository(private val supabaseClient: SupabaseClient): IReportDetailRepository {
    override suspend fun getReport(reportId: Int): Report {
        return supabaseClient.client.postgrest.from("reports").select{
            filter {
                eq("report_id", reportId)
            }
        }.decodeSingle<Report>()
    }

    override suspend fun readFile(
        fileName: String,
        onImageUrlRetrieved: (url: String) -> Unit,
    ) {
        val url = supabaseClient.client.storage.from("images").createSignedUrl("$fileName.jpg", 60.minutes)
        onImageUrlRetrieved(url)
    }
}