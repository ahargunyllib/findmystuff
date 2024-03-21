package com.ahargunyllib.internraion.features.presentation.screen.chat_list.chat_room.payment

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ahargunyllib.internraion.R
import com.ahargunyllib.internraion.ui.component.PrimaryButton
import com.ahargunyllib.internraion.ui.theme.Green
import com.ahargunyllib.internraion.ui.theme.LightGreen
import com.ahargunyllib.internraion.ui.theme.Type
import com.ahargunyllib.internraion.ui.theme.White
import com.ahargunyllib.internraion.ui.theme.Yellow
import com.ahargunyllib.internraion.utils.Routes

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmPaymentScreen(navController: NavController) {


    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .background(Yellow)
                    .padding(horizontal = 8.dp, vertical = 8.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back_button),
                            contentDescription = "",
                            modifier = Modifier
                                .size(32.dp),
                            tint = White
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "KONFIRMASI PEMBAYARAN",
                        style = Type.paymentTitle(),
                        color = White,
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .height(190.dp)
                    .fillMaxWidth()
                    .background(color = Yellow)
                    .fillMaxHeight(),

                ) {
            }


            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(64.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 18.dp, end = 18.dp)
                        .clip(shape = RoundedCornerShape(10.dp))
                        .background(color = Green),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 15.dp, end = 15.dp, top = 9.dp)
                            .clip(shape = RoundedCornerShape(10.dp))
                            .background(color = Yellow),
                    ) {
                        Text(
                            text = "SUMBER DANA",
                            style = Type.confirmInfo(),
                            modifier = Modifier.padding(top = 2.dp, bottom = 5.dp, start = 5.dp)
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            AsyncImage(
                                model = R.drawable.dummy_avatar, contentDescription = null,
                                modifier = Modifier
                                    .padding(start = 7.dp, bottom = 5.dp, end = 9.dp)
                                    .size(width = 45.dp, height = 45.dp)
                                    .clip(shape = RoundedCornerShape(100))
                                    .background(Color.Gray, shape = RoundedCornerShape(64.dp)),
                            )
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.Start
                            ) {
                                Text(
                                    text = "Richard Atilla",
                                    style = Type.confirmInfoBold(),
                                    modifier = Modifier.padding()
                                )
                                Text(text = "GoPay | +6287861****", style = Type.confirmInfo())
                            }
                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 15.dp, end = 15.dp, top = 9.dp, bottom = 15.dp)
                            .clip(shape = RoundedCornerShape(10.dp))
                            .background(color = Yellow),
                    ) {
                        Text(
                            text = "TUJUAN",
                            style = Type.confirmInfo(),
                            modifier = Modifier.padding(top = 2.dp, bottom = 5.dp, start = 5.dp)
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            AsyncImage(
                                model = R.drawable.dummy_avatar, contentDescription = null,
                                modifier = Modifier
                                    .padding(start = 7.dp, bottom = 5.dp, end = 9.dp)
                                    .size(width = 45.dp, height = 45.dp)
                                    .clip(shape = RoundedCornerShape(100))
                                    .background(Color.Gray, shape = RoundedCornerShape(64.dp)),
                            )
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.Start
                            ) {
                                Text(
                                    text = "Syifani",
                                    style = Type.confirmInfoBold(),
                                    modifier = Modifier.padding()
                                )
                                Text(text = "GoPay | +6287861****", style = Type.confirmInfo())
                            }
                        }
                    }

                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                ) {
                    Text(
                        "Note: ",
                        modifier = Modifier.padding(start = 26.dp, bottom = 5.dp),
                        style = Type.paymentNoteStart()
                    )
                    TextField(
                        value = "Terima kasih sudah menemukan barang saya!",
                        onValueChange = { },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding()
                            .align(Alignment.CenterHorizontally)
                            .clip(shape = RoundedCornerShape(5.dp))
                            .padding(start = 46.dp, end = 46.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = LightGreen,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        ),
                        leadingIcon = {
                        },
                        textStyle = Type.paymentNoteCenter(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text
                        )
                    )
                }
                HorizontalDivider(modifier = Modifier.padding(top = 18.dp, bottom = 18.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp, end = 15.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextField(
                        prefix = {
                            Text(
                                text = "Amount Transfer",
                                style = Type.paymentNoteStart(),
                                modifier = Modifier.align(
                                    Alignment.Start
                                )
                            )
                        },
                        value = "Rp. 125.000",
                        textStyle = Type.paymentNoteEnd(),
                        onValueChange = { },
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(
                                Alignment.End
                            ),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        ),
                    )
                    TextField(
                        prefix = {
                            Text(
                                text = "Transaction Fee",
                                style = Type.paymentNoteStart(),
                                modifier = Modifier.align(
                                    Alignment.Start
                                )
                            )
                        },
                        value = "Rp. 6.500",
                        textStyle = Type.paymentNoteEnd(),
                        onValueChange = { },
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(
                                Alignment.End
                            ),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        ),
                    )
                    TextField(
                        prefix = {
                            Text(
                                text = "Total",
                                style = Type.paymentNoteStart(),
                                modifier = Modifier.align(
                                    Alignment.Start
                                )
                            )
                        },
                        value = "Rp. 131.500",
                        textStyle = Type.paymentTotal(),
                        onValueChange = { },
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(
                                Alignment.End
                            ),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        ),
                    )

                    Spacer(modifier = Modifier.size(50.dp))

                    PrimaryButton(
                        "Konfirmasi",
                        onClick = { navController.navigate(Routes.RECEIPT) })


                }

            }
        }
    }
}

