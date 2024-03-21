package com.ahargunyllib.internraion.features.presentation.screen.report.location_picker

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState

class LocationPickerViewModel() : ViewModel(){
    val cameraPositionState = mutableStateOf(CameraPositionState(
        position = CameraPosition(
            LatLng(-7.952586062167603, 112.61445825692978),
            17f,
            0f,
            0f
        )
    ))
}