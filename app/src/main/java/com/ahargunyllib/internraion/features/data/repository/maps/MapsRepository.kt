package com.ahargunyllib.internraion.features.data.repository.maps

import com.ahargunyllib.internraion.features.data.network.SupabaseClient
import com.ahargunyllib.internraion.features.domain.model.Report
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.flow.Flow

class MapsRepository(private val supabaseClient: SupabaseClient): IMapsRepository{
    override suspend fun getReports(): List<Report> {
        return supabaseClient.client.postgrest.from("reports").select().decodeList<Report>()
    }
}