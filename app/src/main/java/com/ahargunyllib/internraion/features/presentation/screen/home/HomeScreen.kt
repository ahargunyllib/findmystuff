package com.ahargunyllib.internraion.features.presentation.screen.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.ahargunyllib.internraion.features.data.network.SupabaseClient
import com.ahargunyllib.internraion.features.data.repository.user.UserRepository
import com.ahargunyllib.internraion.features.presentation.navigation.BottomNavigationBar
import com.ahargunyllib.internraion.utils.Routes

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController) {
    val viewModel = HomeViewModel(userRepository = UserRepository(supabaseClient = SupabaseClient))
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.isUserLoggedIn(
            context
        )

        if (!viewModel.isUserLoggedIn) {
            Log.i("home screen", "HomeScreen: user not login")
            navController.navigate(Routes.WELCOME)
        }
    }
    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) {
        Row {
            Text(text = "Home Screen")
            Button(onClick = { navController.navigate(Routes.MAPS) }) {
                Text(text = "to maps")
            }
            Button(onClick = { navController.navigate("${Routes.REPORT}/0.0/0.0") }) {
                Text(text = "to report")
            }
        }
        Button(onClick = { navController.navigate(Routes.PROFILE) }) {
            Text(text = "to profile")
        }

    }
}

