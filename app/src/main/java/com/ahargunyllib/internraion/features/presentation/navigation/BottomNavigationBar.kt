package com.ahargunyllib.internraion.features.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.GifBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.ReportProblem
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ahargunyllib.internraion.ui.theme.White
import com.ahargunyllib.internraion.utils.Routes


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomNavigationBar(navController: NavController) {

    val context = LocalContext.current.applicationContext
    val selected = remember {
        mutableStateOf(Icons.Default.Home)
    }

    BottomAppBar(
        containerColor = White,

    ) {
        // Home
        IconButton(
            onClick = {
                selected.value = Icons.Default.Home
                navController.navigate(Routes.HOME) {
                    popUpTo(0)
                }
            },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                Icons.Default.Home,
                contentDescription = null,
                modifier = Modifier.size(26.dp),
                tint = if (selected.value == Icons.Default.Home) White else Color.DarkGray
            )
        }
        // Locatiion
        IconButton(
            onClick = {
                selected.value = Icons.Default.LocationOn
                navController.navigate(Routes.MAPS)
            },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                Icons.Default.LocationOn,
                contentDescription = null,
                modifier = Modifier.size(26.dp),
                tint = if (selected.value == Icons.Default.LocationOn) White else Color.DarkGray
            )
        }
        // Report
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.Default.ReportProblem,
                contentDescription = null,
                modifier = Modifier
                    .size(26.dp)
                    .clickable { navController.navigate("${Routes.REPORT}/0.0/0.0") },
                tint = if (selected.value == Icons.Default.ReportProblem) White else Color.DarkGray
            )
        }
        // Chat
        IconButton(
            onClick = {
                selected.value = Icons.Default.ChatBubble
                navController.navigate(Routes.COMING_SOON)
            },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                Icons.Default.ChatBubble,
                contentDescription = null,
                modifier = Modifier.size(26.dp),
                tint = if (selected.value == Icons.Default.ChatBubble) White else Color.DarkGray
            )
        }
        // Status
        IconButton(
            onClick = {
                selected.value = Icons.Default.GifBox
                navController.navigate(Routes.COMING_SOON)
            },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                Icons.Default.GifBox,
                contentDescription = null,
                modifier = Modifier.size(26.dp),
                tint = if (selected.value == Icons.Default.GifBox) White else Color.DarkGray
            )
        }


    }

}