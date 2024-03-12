package com.ahargunyllib.internraion.features.data.repository.report

import android.content.ContentValues
import android.util.Log
import com.ahargunyllib.internraion.features.data.network.SupabaseClient
import com.ahargunyllib.internraion.features.domain.model.Report
import com.ahargunyllib.internraion.features.domain.model.User
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.user.UserSession
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
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

    override suspend fun createReport(report: Report) {
        supabaseClient.client.postgrest.from("reports").insert(report)
    }

    override suspend fun getCurrentUserId(): Int? {
        val email = supabaseClient.client.auth.currentSessionOrNull()?.user?.email

        val user =  supabaseClient.client.postgrest.from("users").select {
            filter {
                if (email != null) {
                    eq("email", email)
                }
            }
        }.decodeSingle<User>()

        return user.userId
    }

    override suspend fun getReportId(reportName: String): Int? {
        val report = supabaseClient.client.postgrest.from("reports").select {
            filter {
                eq("name", reportName)
            }
        }.decodeSingle<Report>()

        return report.reportId
    }
}