package com.ahargunyllib.internraion.features.presentation.screen.maps

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahargunyllib.internraion.features.data.repository.maps.MapsRepository
import com.ahargunyllib.internraion.features.data.repository.report.ReportRepository
import com.ahargunyllib.internraion.features.data.utils.ReportResponse
import com.ahargunyllib.internraion.features.data.utils.ReportsResponse
import com.ahargunyllib.internraion.features.domain.model.Report
import com.google.android.gms.maps.model.MapStyleOptions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MapsViewModel(private val mapsRepository: MapsRepository) : ViewModel(){
    private val _state = MutableStateFlow<ReportsResponse>(ReportsResponse.Loading)
    val state = _state.asStateFlow()

    init {
        getReports()
        Log.i("MAPS VIEW MODEL", "getting reports: ${state.value}")
    }

    private fun getReports(){
        viewModelScope.launch {
            mapsRepository.getReports().collect {
                _state.value = it
                Log.i("MAPS VIEW MODEL", "getReports: ")
            }
        }
    }


}
