package com.ahargunyllib.internraion.features.data.repository.report

import com.ahargunyllib.internraion.features.data.network.SupabaseClient
import com.ahargunyllib.internraion.features.domain.model.Report
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.storage.storage
import kotlin.time.Duration.Companion.minutes

class ReportRepository (
    private val supabaseClient: SupabaseClient
): IReportRepository {
    override suspend fun uploadFile(
        fileName: String,
        byteArray: ByteArray,
    ) {
        supabaseClient.client.storage.from("images").upload("$fileName.jpg", byteArray, true)
    }

    override suspend fun readFile(
        fileName: String,
        onImageUrlRetrieved: (url: String) -> Unit,
    ) {
        val url = supabaseClient.client.storage.from("images").createSignedUrl(fileName, 60.minutes)
        onImageUrlRetrieved(url)
    }

    override suspend fun createReport(report: Report) {
        supabaseClient.client.postgrest["reports"].insert(report)
    }
}