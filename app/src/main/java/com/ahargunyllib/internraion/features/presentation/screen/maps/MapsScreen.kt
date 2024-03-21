package com.ahargunyllib.internraion.features.presentation.screen.maps

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.ahargunyllib.internraion.features.data.network.SupabaseClient
import com.ahargunyllib.internraion.features.data.repository.maps.MapsRepository
import com.ahargunyllib.internraion.features.data.utils.ReportsResponse
import com.ahargunyllib.internraion.features.presentation.navigation.BottomNavigationBar
import com.ahargunyllib.internraion.utils.Routes
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
    val state = viewModel.state.collectAsState()
    val context = LocalContext.current

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            uiSettings = uiSettings,
            cameraPositionState = CameraPositionState(
                position = CameraPosition(
                    LatLng(-7.952586062167603, 112.61445825692978),
                    17f,
                    0f,
                    0f
                )
            )
        ) {
            when (state.value) {
                is ReportsResponse.Loading -> {
                    Log.i("REPORTS RESPONSE LOADING", "MapsScreen: LOADING")
                }

                is ReportsResponse.Success -> {
                    val reports = (state.value as ReportsResponse.Success).data
                    Log.i("REPORTS RESPONSE SUCCESS", "MapsScreen: $reports")


                    reports.forEach { report ->
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

                is ReportsResponse.Error -> {
                    val error = (state.value as ReportsResponse.Error).message
                    Toast.makeText(
                        context,
                        (state.value as ReportsResponse.Error).message,
                        Toast.LENGTH_SHORT
                    )
                        .show()
                    Log.i("REPORTS RESPONSE ERROR", "MapsScreen: $error")
                }
            }


        }
    }
}