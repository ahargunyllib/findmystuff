package com.ahargunyllib.internraion.features.presentation.screen.maps

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ahargunyllib.internraion.features.data.repository.maps.MapsRepository
import com.ahargunyllib.internraion.features.data.repository.report.ReportRepository
import com.google.android.gms.maps.model.MapStyleOptions

class MapsViewModel(private val mapsRepository: MapsRepository) : ViewModel(){
    var state by mutableStateOf(MapState())

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
