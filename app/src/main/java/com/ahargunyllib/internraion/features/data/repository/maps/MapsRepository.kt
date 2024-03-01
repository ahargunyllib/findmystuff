package com.ahargunyllib.internraion.features.data.repository.maps

import com.ahargunyllib.internraion.features.data.network.SupabaseClient
import com.ahargunyllib.internraion.features.domain.model.Spot
import kotlinx.coroutines.flow.Flow

class MapsRepository(private val supabaseClient: SupabaseClient): IMapsRepository{
    override suspend fun insertSpot(spot: Spot) {
        TODO()
    }

    override suspend fun deleteSpot(spot: Spot) {
        TODO()
    }

    override fun getSpots(): Flow<List<Spot>> {
        TODO()
    }
}