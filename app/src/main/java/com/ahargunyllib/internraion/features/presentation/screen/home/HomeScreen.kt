package com.ahargunyllib.internraion.features.presentation.screen.home

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.ahargunyllib.internraion.ui.theme.InternraionTheme
import com.ahargunyllib.internraion.util.Routes

@Composable
fun HomeScreen(navController: NavController) {
    InternraionTheme {
        Row {
            Text(text = "Home Screen")
            Button(onClick = { navController.navigate(Routes.MAPS) }) {
                Text(text = "to maps")
            }
        }
    }
}