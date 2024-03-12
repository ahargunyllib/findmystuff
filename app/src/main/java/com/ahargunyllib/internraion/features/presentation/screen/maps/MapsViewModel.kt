package com.ahargunyllib.internraion.features.presentation.screen.maps

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahargunyllib.internraion.features.data.repository.maps.MapsRepository
import com.ahargunyllib.internraion.features.data.repository.report.ReportRepository
import com.ahargunyllib.internraion.features.domain.model.Report
import com.google.android.gms.maps.model.MapStyleOptions
import kotlinx.coroutines.launch

class MapsViewModel(private val mapsRepository: MapsRepository) : ViewModel(){
    var state by mutableStateOf(MapState())
    var reports by mutableStateOf<List<Report>>(emptyList())

    init {
        getReports()
    }

    private fun getReports(){
        viewModelScope.launch {
            reports = mapsRepository.getReports()
            Log.i("VIEW MODEL", "getReports: $reports")
        }
    }
    fun onEvent(event: MapEvent){
        when(event){
            is MapEvent.ToggleFalloutMap -> {
                state = state.copy(
                    properties = state.properties.copy(
                        mapStyleOptions = if(state.isFalloutMap) {
                            null
                        } else MapStyleOptions(MapStyle.json),
                    ),
                    isFalloutMap = !state.isFalloutMap
                )
            }
        }
    }


}
