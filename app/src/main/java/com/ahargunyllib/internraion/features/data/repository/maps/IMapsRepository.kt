package com.ahargunyllib.internraion.features.data.repository.maps

import com.ahargunyllib.internraion.features.domain.model.Spot
import kotlinx.coroutines.flow.Flow

interface IMapsRepository {
    suspend fun insertSpot(spot: Spot)
    suspend fun deleteSpot(spot: Spot)
    fun getSpots(): Flow<List<Spot>>
}