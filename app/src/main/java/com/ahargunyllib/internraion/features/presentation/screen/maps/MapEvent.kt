package com.ahargunyllib.internraion.features.presentation.screen.maps

sealed class MapEvent {
    data object ToggleFalloutMap: MapEvent()
}