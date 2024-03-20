package com.ahargunyllib.internraion.features.presentation.screen.payment

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ahargunyllib.internraion.R
import com.ahargunyllib.internraion.ui.theme.Green
import com.ahargunyllib.internraion.ui.theme.LightGreen
import com.ahargunyllib.internraion.ui.theme.Type
import com.ahargunyllib.internraion.ui.theme.White
import com.ahargunyllib.internraion.ui.theme.Yellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(navController: NavController) {
    val radioOptions = listOf("GOPAY", "SHOPEEPAY", "OVO")
    var selectedOption by remember { mutableStateOf(radioOptions[0]) }

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
        Icon(
            painter = painterResource(id = R.drawable.ic_payment_backwhite),
            contentDescription = "",
            modifier = Modifier
                .padding(start = 20.dp, top = 16.dp)
                .width(15.dp)
                .height(25.dp)
                .clickable { navController.popBackStack() },
            tint = White
        )


        Column(
            modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Pembayaran",
                style = Type.paymentTitle(),
                modifier = Modifier.padding(top = 58.dp, bottom = 43.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 18.dp, end = 18.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(color = Green),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp, end = 15.dp, top = 9.dp)
                        .clip(shape = RoundedCornerShape(10.dp))
                        .background(color = Yellow),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Pilih metode Pembayaran",
                        style = Type.paymentChoose(),
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp, end = 15.dp, top = 9.dp, bottom = 15.dp)
                        .clip(shape = RoundedCornerShape(10.dp))
                        .background(color = Yellow),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Column {
                        Spacer(modifier = Modifier.size(10.dp))
                        radioOptions.forEach { option ->
                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 10.dp, top = 10.dp, start = 7.dp, end = 7.dp)
                                    .clip(shape = RoundedCornerShape(10.dp))
                                    .background(color = White)
                                    .clickable { selectedOption = option },
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                if (option == selectedOption) {
                                    AsyncImage(
                                        model = R.drawable.ic_payment_checkyyellow,
                                        contentDescription = null,
                                        modifier = Modifier
                                            .padding(5.dp)
                                            .width(22.dp)
                                            .height(14.dp)
                                    )
                                } else {
                                }
                                Text(
                                    text = option,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 5.dp, bottom = 5.dp),
                                    style = Type.paymentChoose(),
                                    textAlign = TextAlign.Center,
                                )
                            }
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
                    style = Type.paymentNote()
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
                    textStyle = Type.paymentNote(),
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
            ) {

            }
            TextField(
                prefix = { Text(text = "Amount Transfer", style = Type.paymentNote()) },
                value = "Rp. 125.000",
                textStyle = Type.paymentNote(),
                onValueChange = { },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
            )


        }
    }
}