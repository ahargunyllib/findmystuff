package com.ahargunyllib.internraion.features.data.repository.maps

import com.ahargunyllib.internraion.features.domain.model.Report
import kotlinx.coroutines.flow.Flow

interface IMapsRepository {
    suspend fun insertSpot(report: Report)
    suspend fun deleteSpot(report: Report)
    fun getSpots(): Flow<List<Report>>
}