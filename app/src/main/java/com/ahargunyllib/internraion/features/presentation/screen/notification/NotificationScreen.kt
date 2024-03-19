package com.ahargunyllib.internraion.features.presentation.screen.notification

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ahargunyllib.internraion.R
import com.ahargunyllib.internraion.ui.theme.Green
import com.ahargunyllib.internraion.ui.theme.Type

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NotificationScreen(navController: NavController) {
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(57.dp)
                    .shadow(elevation = 4.dp)
                    .background(Color.White),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back_button),
                    contentDescription = "",
                    modifier = Modifier
                        .size(30.dp)
                        .padding(start = 5.dp, top = 5.dp)
                        .clickable { navController.popBackStack() },
                    tint = Green
                )
                Text(
                    text = "NOTIFIKASI",
                    style = Type.textMedium(),
                    fontSize = 17.sp,
                    fontWeight = FontWeight(600),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )

            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp, top = 64.dp, bottom = 3.dp, end = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            items(15) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(
                            start = 9.dp,
                            end = 9.dp,
                            top = 5.dp,
                            bottom = 5.dp
                        )
                    ) {
                        AsyncImage(
                            model = R.drawable.dummy_avatar,
                            contentDescription = null,
                            modifier = Modifier
                                .size(width = 55.dp, height = 55.dp)
                                .clip(shape = RoundedCornerShape(100))
                                .background(Color.Gray, shape = RoundedCornerShape(64.dp)),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.size(15.dp))
                        Column {
                            Text(
                                text = " 'XX' sepertinya menemukan barang kamu !!!",
                                style = Type.notifFirst()
                            )
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = "12 June 2023 19:34", style = Type.notifSecond())
                                IconButton(onClick = { /*TODO*/ }) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowForwardIos,
                                        contentDescription = null,
                                        modifier = Modifier
                                            .padding(1.dp)
                                            .width(12.dp)
                                            .height(12.dp)
                                    )
                                }

                            }
                        }
                    }
                }
            }
        }
    }
}