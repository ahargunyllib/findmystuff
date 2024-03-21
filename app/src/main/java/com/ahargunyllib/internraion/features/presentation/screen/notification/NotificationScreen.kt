package com.ahargunyllib.internraion.features.presentation.screen.notification

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ahargunyllib.internraion.R
import com.ahargunyllib.internraion.ui.component.SingleIconTopBar
import com.ahargunyllib.internraion.ui.theme.Type

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NotificationScreen(navController: NavController) {
    Scaffold(
        topBar = {
            SingleIconTopBar(title = "NOTIFIKASI", navController = navController)
        },
        content = {
            LazyColumn(
                modifier = Modifier
                    .padding(top = 64.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                items(15) {
                    NotificationItem()
                    HorizontalDivider()
                }
            }
        }
    )
}

@Composable
fun NotificationItem() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(
                horizontal = 16.dp,
                vertical = 8.dp
            )
        ) {
            AsyncImage(
                model = R.drawable.dummy_avatar,
                contentDescription = "User Avatar",
                modifier = Modifier
                    .size(width = 64.dp, height = 64.dp)
                    .clip(shape = RoundedCornerShape(100))
                    .background(
                        Color.Gray, shape = RoundedCornerShape(64.dp)
                    ),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.size(8.dp))
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "'XX' sepertinya menemukan barang kamu !!!",
                    style = Type.notifFirst()
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(text = "12 June 2023 19:34", style = Type.notifSecond())
                }
            }
        }
    }
}