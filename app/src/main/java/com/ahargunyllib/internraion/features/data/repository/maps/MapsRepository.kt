package com.ahargunyllib.internraion.features.data.repository.maps

import android.util.Log
import com.ahargunyllib.internraion.features.data.network.SupabaseClient
import com.ahargunyllib.internraion.features.domain.model.Report
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.flow.Flow

class MapsRepository(private val supabaseClient: SupabaseClient): IMapsRepository{
    override suspend fun getReports(): List<Report> {
        val reports = supabaseClient.client.postgrest.from("reports").select().decodeList<Report>()
        Log.i("REPOS", "getReports: $reports")
        return reports
    }
}