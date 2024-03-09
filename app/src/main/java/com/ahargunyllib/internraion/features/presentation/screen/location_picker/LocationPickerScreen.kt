package com.ahargunyllib.internraion.features.presentation.screen.location_picker

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ahargunyllib.internraion.utils.Routes
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LocationPickerScreen(navController: NavController) {
    val viewModel = LocationPickerViewModel()

    Scaffold(
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            GoogleMap(
                cameraPositionState = viewModel.cameraPositionState.value
            ) {
                Marker(
                    position = viewModel.cameraPositionState.value.position.target
                )
            }

            Text(
                text = "Latitude: ${viewModel.cameraPositionState.value.position.target.latitude}, Longitude: ${viewModel.cameraPositionState.value.position.target.longitude}",
                modifier = Modifier.padding(16.dp)
            )
            Button(onClick = {
                navController.navigate("${Routes.REPORT}/${viewModel.cameraPositionState.value.position.target.latitude}/${viewModel.cameraPositionState.value.position.target.longitude}")
            }) {
                Text(text = "done")
            }
        }
    }
}