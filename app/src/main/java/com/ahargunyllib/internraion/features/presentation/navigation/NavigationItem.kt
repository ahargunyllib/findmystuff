package com.ahargunyllib.internraion.features.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.GifBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.ReportProblem
import androidx.compose.ui.graphics.vector.ImageVector
import com.ahargunyllib.internraion.utils.Routes

sealed class NavigationItem (
    val route: String,
    val icon: ImageVector,
    val selectedIcon: ImageVector,
    val title: String
){
    object Home: NavigationItem(Routes.HOME, Icons.Default.Home, Icons.Default.Home, "Home" )
    object Location: NavigationItem(Routes.MAPS, Icons.Default.LocationOn, Icons.Default.LocationOn, "Location")
    object Report : NavigationItem(Routes.REPORT, Icons.Default.ReportProblem, Icons.Default.ReportProblem, "Report")
    object Chat: NavigationItem(Routes.COMING_SOON, Icons.Default.ChatBubble, Icons.Default.ChatBubble, "Chat")
    object Status: NavigationItem(Routes.COMING_SOON, Icons.Default.GifBox, Icons.Default.GifBox, "Status")
}