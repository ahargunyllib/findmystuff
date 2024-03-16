package com.ahargunyllib.internraion.features.data.repository.maps

import android.util.Log
import com.ahargunyllib.internraion.features.data.network.SupabaseClient
import com.ahargunyllib.internraion.features.data.utils.ReportResponse
import com.ahargunyllib.internraion.features.data.utils.ReportsResponse
import com.ahargunyllib.internraion.features.domain.model.Report
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MapsRepository(private val supabaseClient: SupabaseClient): IMapsRepository{
    override suspend fun getReports(): Flow<ReportsResponse> = flow {
        emit(ReportsResponse.Loading)
        try {
            val reports = supabaseClient.client.postgrest.from("reports").select().decodeList<Report>()
            Log.i("MAPS REPOS", "getReports success: $reports")
            emit(ReportsResponse.Success(reports))
        } catch (e: Exception) {
            emit(ReportsResponse.Error(e.message ?: ""))
        }
    }.flowOn(Dispatchers.IO)
}