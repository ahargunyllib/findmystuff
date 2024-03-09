package com.ahargunyllib.internraion.features.presentation.screen.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.ahargunyllib.internraion.ui.theme.InternraionTheme
import com.ahargunyllib.internraion.utils.Routes

@Composable
fun WelcomeScreen(navController: NavController) {
    InternraionTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Welcome")
                Button(onClick = { navController.navigate(Routes.REGISTER) }) {
                    Text("Register")
                }
                Button(onClick = { navController.navigate(Routes.LOGIN) }) {
                    Text("Login")
                }

            }
        }
    }
}