package com.ahargunyllib.internraion.features.presentation.screen.home

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.ahargunyllib.internraion.utils.Routes

@Composable
fun HomeScreen(navController: NavController) {
    Row {
        Text(text = "Home Screen")
        Button(onClick = { navController.navigate(Routes.MAPS) }) {
            Text(text = "to maps")
        }
        Button(onClick = { navController.navigate("${Routes.REPORT}/0.0/0.0") }) {
            Text(text = "to report")
        }
        Button(onClick = { navController.navigate(Routes.PROFILE) }) {
            Text(text = "to profile")
        }
    }
}