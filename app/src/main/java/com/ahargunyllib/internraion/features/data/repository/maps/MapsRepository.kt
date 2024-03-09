package com.ahargunyllib.internraion.features.data.repository.maps

import com.ahargunyllib.internraion.features.data.network.SupabaseClient
import com.ahargunyllib.internraion.features.domain.model.Report
import kotlinx.coroutines.flow.Flow

class MapsRepository(private val supabaseClient: SupabaseClient): IMapsRepository{
    override suspend fun insertSpot(report: Report) {
        TODO()
    }

    override suspend fun deleteSpot(report: Report) {
        TODO()
    }

    override fun getSpots(): Flow<List<Report>> {
        TODO()
    }
}