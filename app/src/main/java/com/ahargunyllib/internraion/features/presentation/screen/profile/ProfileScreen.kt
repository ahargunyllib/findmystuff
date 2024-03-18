package com.ahargunyllib.internraion.features.presentation.screen.profile

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.ahargunyllib.internraion.features.data.repository.user.UserRepository
import com.ahargunyllib.internraion.features.data.network.SupabaseClient
import com.ahargunyllib.internraion.utils.Routes

@Composable
fun ProfileScreen(navController: NavController) {
    val viewModel =
        ProfileViewModel(userRepository = UserRepository(supabaseClient = SupabaseClient))
    val context = LocalContext.current

    Button(onClick = {
        viewModel.signOutUser(context = context)
        navController.navigate(Routes.LOGIN)
    }) {
        Text("Log out")
    }
}