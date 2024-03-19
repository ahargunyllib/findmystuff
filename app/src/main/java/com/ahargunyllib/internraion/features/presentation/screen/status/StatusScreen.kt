package com.ahargunyllib.internraion.features.presentation.screen.status

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ahargunyllib.internraion.features.presentation.navigation.BottomNavigationBar
import com.ahargunyllib.internraion.ui.theme.Green
import com.ahargunyllib.internraion.ui.theme.Orange
import com.ahargunyllib.internraion.ui.theme.Type
import com.ahargunyllib.internraion.ui.theme.White
import com.ahargunyllib.internraion.ui.theme.Yellow
import com.ahargunyllib.internraion.utils.Routes

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun StatusScreen(navController: NavController) {
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(elevation = 4.dp)
                    .background(Color.White),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(onClick = { navController.navigate(Routes.NOTIFICATION) }) {
                    Icon(
                        Icons.Default.Notifications,
                        contentDescription = "",
                        modifier = Modifier
                            .size(36.dp),
                        tint = Yellow
                    )
                }
                Text(
                    text = "STATUS", style = Type.textMedium(), modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f), textAlign = TextAlign.Center
                )
                IconButton(onClick = { navController.navigate(Routes.PROFILE) }) {
                    Icon(
                        Icons.Default.Person,
                        contentDescription = "",
                        modifier = Modifier
                            .size(36.dp),
                        tint = Yellow
                    )
                }

            }
        },
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 50.dp, bottom = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            items(2) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 6.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Yellow),
                        horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier.padding(start = 13.dp, top = 10.dp, bottom = 8.dp)
                        ) {
                            Text(text = "TUMBLER HIJAU", style = Type.statusBold())
                            Text(text = "Status: Proses Pencarian", style = Type.statusRegular())
                            Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(containerColor = Orange)) {
                                Text(text = "Konfirmasi Barang", style = Type.statusBold(), color = White)
                            }

                        }
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Default.ArrowForwardIos,
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(1.dp)
                                    .size(30.dp)
                            )

                        }
                    }
                }
            }
            items(1) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 6.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Yellow),
                        horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier.padding(start = 13.dp, top = 10.dp, bottom = 8.dp)
                        ) {
                            Text(text = "KAOS JERSEY BIRU DONGKER DI PERPUSTAKAAN", style = Type.statusBold(), maxLines = 1)
                            Text(text = "Status: Ditemukan", style = Type.statusRegular())
                        }
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Default.ArrowForwardIos,
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(1.dp)
                                    .size(30.dp)
                            )

                        }
                    }
                }
            }

            items(2) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 6.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Yellow),
                        horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier.padding(start = 13.dp, top = 10.dp, bottom = 8.dp)
                        ) {
                            Text(text = "SLAYER ABU-ABU DENGAN VAPE", style = Type.statusBold())
                            Text(text = "Status: Barang Diterima", style = Type.statusRegular())
                        }
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Default.ArrowForwardIos,
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(1.dp)
                                    .size(30.dp)
                            )

                        }
                    }
                }
            }

            items(1) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 6.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Yellow),
                        horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier.padding(start = 13.dp, top = 10.dp, bottom = 8.dp)
                        ) {
                            Text(text = "TUMBLER BIRU BTS JUNGKOOK DI CL...", style = Type.statusBold())
                            Text(text = "Status: Barang Diterima", style = Type.statusRegular())
                            Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(containerColor = Green)) {
                                Text(text = "Barang Diterima", style = Type.statusBold(), color = White)
                            }

                        }
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Default.ArrowForwardIos,
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(1.dp)
                                    .size(30.dp)
                            )

                        }
                    }
                }
            }

            items(1) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 6.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Yellow),
                        horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier.padding(start = 13.dp, top = 10.dp, bottom = 8.dp)
                        ) {
                            Text(text = "LAPTOP ASUS STICKER INAUGURASI...", style = Type.statusBold())
                            Text(text = "Status: Hilang", style = Type.statusRegular())
                        }
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Default.ArrowForwardIos,
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(1.dp)
                                    .size(30.dp)
                            )

                        }
                    }
                }
            }

            items(2) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 6.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Yellow),
                        horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier.padding(start = 13.dp, top = 10.dp, bottom = 8.dp)
                        ) {
                            Text(text = "OUTER VANILLA BLUE DEPAN BANK M...", style = Type.statusBold())
                            Text(text = "Status: Proses Pencarian", style = Type.statusRegular())
                            Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(containerColor = Green)) {
                                Text(text = "Barang Diterima", style = Type.statusBold(), color = White)
                            }

                        }
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Default.ArrowForwardIos,
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(1.dp)
                                    .size(30.dp)
                            )

                        }
                    }
                }
            }

            items(2) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 6.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Yellow),
                        horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier.padding(start = 13.dp, top = 10.dp, bottom = 8.dp)
                        ) {
                            Text(text = "HP IPHONE 16+ DI GKM FILKOM LT.4...", style = Type.statusBold())
                            Text(text = "Status: Ditemukan", style = Type.statusRegular())

                        }
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Default.ArrowForwardIos,
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(1.dp)
                                    .size(30.dp)
                            )

                        }
                    }
                }
            }








        }
    }
}