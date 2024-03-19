package com.ahargunyllib.internraion.features.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ahargunyllib.internraion.R
import com.ahargunyllib.internraion.ui.theme.Green
import com.ahargunyllib.internraion.ui.theme.White
import com.ahargunyllib.internraion.ui.theme.Yellow
import com.ahargunyllib.internraion.utils.Routes


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomNavigationBar(navController: NavController) {

    val context = LocalContext.current.applicationContext
    val selected = remember {
        mutableStateOf(navController.currentDestination?.route)
    }

    BottomAppBar(
        containerColor = White,
        modifier = Modifier.shadow(elevation = 16.dp)
        ) {
        // Home
        IconButton(
            onClick = {
                selected.value = Routes.HOME
                navController.navigate(Routes.HOME) {
                    popUpTo(0)
                }
            },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_bottombar_home),
                contentDescription = "",
                modifier = if (selected.value != Routes.HOME) Modifier.size(48.dp) else Modifier
                    .size(
                        48.dp
                    )
                    .background(color = Yellow, shape = RoundedCornerShape(100))
                    .padding(2.dp),
                tint = Green,
            )
        }
        // Location
        IconButton(
            onClick = {
                selected.value = Routes.MAPS
                navController.navigate(Routes.MAPS)
            },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_bottombar_map),
                contentDescription = null,
                modifier = if (selected.value != Routes.MAPS) Modifier.size(48.dp) else Modifier
                    .size(
                        48.dp
                    )
                    .background(color = Yellow, shape = RoundedCornerShape(100))
                    .padding(2.dp),
                tint = Green
            )
        }

        // REPORT
        IconButton(
            onClick = {
                selected.value = "${Routes.REPORT}/0.0/0.0"
                navController.navigate("${Routes.REPORT}/0.0/0.0")
            },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_bottombar_lapor),
                contentDescription = null,
                modifier = if (selected.value != "${Routes.REPORT}/0.0/0.0") Modifier.size(48.dp) else Modifier
                    .size(
                        48.dp
                    )
                    .background(color = Yellow, shape = RoundedCornerShape(100))
                    .padding(2.dp),
                tint = Green
            )
        }
        // Chat
        IconButton(
            onClick = {
                selected.value = Routes.CHAT_LIST
                navController.navigate(Routes.CHAT_LIST)
            },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_bottombar_chat),
                contentDescription = null,
                modifier = if (selected.value != Routes.CHAT_LIST) Modifier.size(48.dp) else Modifier
                    .size(
                        48.dp
                    )
                    .background(color = Yellow, shape = RoundedCornerShape(100))
                    .padding(2.dp),
                tint = Green
            )
        }
        // Status
        IconButton(
            onClick = {
                selected.value = Routes.STATUS
                navController.navigate(Routes.STATUS)
            },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_bottombar_status),
                contentDescription = null,
                modifier = if (selected.value != Routes.STATUS) Modifier.size(48.dp) else Modifier
                    .size(
                        48.dp
                    )
                    .background(color = Yellow, shape = RoundedCornerShape(100))
                    .padding(2.dp),
                tint = Green
            )
        }


    }

}