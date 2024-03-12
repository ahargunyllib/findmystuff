package com.ahargunyllib.internraion.features.presentation.screen.maps

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ToggleOff
import androidx.compose.material.icons.filled.ToggleOn
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.ahargunyllib.internraion.features.data.network.SupabaseClient
import com.ahargunyllib.internraion.features.data.repository.maps.MapsRepository
import com.ahargunyllib.internraion.features.domain.model.Report
import com.ahargunyllib.internraion.utils.Routes
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MapsScreen(navController: NavController) {
    val viewModel = MapsViewModel(mapsRepository = MapsRepository(supabaseClient = SupabaseClient))
    val uiSettings = remember {
        MapUiSettings(zoomControlsEnabled = false)
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(MapEvent.ToggleFalloutMap)
            }) {
                Icon(
                    imageVector = if (viewModel.state.isFalloutMap) {
                        Icons.Default.ToggleOff
                    } else Icons.Default.ToggleOn,
                    contentDescription = "Toggle Fallout map"
                )
            }
        }
    ) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            properties = viewModel.state.properties,
            uiSettings = uiSettings,
            cameraPositionState = CameraPositionState(position = CameraPosition(
                LatLng(-7.952586062167603, 112.61445825692978),
                17f,
                0f,
                0f
            ))
        ) {
            Log.i("SCREEN", "MapsScreen: ${viewModel.reports}")
            viewModel.reports.forEach { report ->
                Marker(
                    position = LatLng(report.latitude, report.longitude),
                    title = report.name,
                    onClick = {
                        navController.navigate("${Routes.REPORT_DETAIL}/${report.reportId}")
                        true
                    }
                )

            }
        }
    }
}