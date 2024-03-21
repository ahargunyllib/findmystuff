package com.ahargunyllib.internraion.features.presentation.screen.chat_list.chat_room.payment

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ahargunyllib.internraion.R
import com.ahargunyllib.internraion.ui.component.PrimaryButton
import com.ahargunyllib.internraion.ui.component.SingleIconTopBar
import com.ahargunyllib.internraion.ui.theme.Green
import com.ahargunyllib.internraion.ui.theme.LightGreen
import com.ahargunyllib.internraion.ui.theme.Type
import com.ahargunyllib.internraion.ui.theme.White
import com.ahargunyllib.internraion.ui.theme.Yellow
import com.ahargunyllib.internraion.utils.Routes

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(navController: NavController) {
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
                ){
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
                        text = "PEMBAYARAN",
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
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Spacer(modifier = Modifier.height(64.dp))

                PaymentMethods()

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

                Summary(navController)
            }
        }
    }
}

@Composable
fun PaymentMethods() {
    val radioOptions = listOf("GOPAY", "SHOPEEPAY", "OVO")
    var selectedOption by remember { mutableStateOf(radioOptions[0]) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(8.dp))
            .background(color = Green),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clip(shape = RoundedCornerShape(8.dp))
                .background(color = Yellow),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Pilih metode Pembayaran",
                style = Type.paymentChoose(),
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clip(shape = RoundedCornerShape(8.dp))
                .background(color = Yellow),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, top = 8.dp)
                    .background(color = Yellow),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                radioOptions.forEach { option ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                width = 1.dp,
                                color = Color(0xFF6C6C6C),
                                shape = RoundedCornerShape(size = 8.dp)
                            )
                            .background(Color.White, shape = RoundedCornerShape(8.dp))
                            .clip(shape = RoundedCornerShape(8.dp))
                            .clickable { selectedOption = option }
                            .padding(horizontal = 8.dp, vertical = 8.dp),
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ){
                            AsyncImage(
                                model = if (option == selectedOption) R.drawable.ic_payment_checkyyellow else null,
                                contentDescription = null,
                                modifier = Modifier
                                    .width(22.dp)
                                    .height(14.dp)
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = option,
                                style = Type.paymentChoose(),
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Summary(navController: NavController) {
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

        PrimaryButton(text = "Lanjut", onClick = { navController.navigate(Routes.CONFIRM_PAYMENT) } )

    }
}