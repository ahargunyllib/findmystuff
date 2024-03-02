package com.ahargunyllib.internraion.features.presentation.screen.home

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.ahargunyllib.internraion.ui.theme.InternraionTheme

@Composable
fun HomeScreen(navController: NavController) {
    InternraionTheme {
        Row {
            Text(text = "Home Screen")
        }
    }
}