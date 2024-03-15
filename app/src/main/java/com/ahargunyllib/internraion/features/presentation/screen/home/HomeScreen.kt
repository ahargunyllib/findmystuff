package com.ahargunyllib.internraion.features.presentation.screen.home

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.ahargunyllib.internraion.features.data.network.SupabaseClient
import com.ahargunyllib.internraion.features.data.repository.user.UserRepository
import com.ahargunyllib.internraion.features.data.utils.Response
import com.ahargunyllib.internraion.features.presentation.navigation.BottomNavigationBar
import com.ahargunyllib.internraion.utils.Routes
import kotlinx.coroutines.Delay
import java.util.Timer
import kotlin.concurrent.schedule

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController) {
    val viewModel = HomeViewModel(userRepository = UserRepository(supabaseClient = SupabaseClient))
    val state = viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.isUserLoggedIn()
    }

    when (state.value) {
        is Response.Loading -> {}

        is Response.Success -> {}

        is Response.Error -> {
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

