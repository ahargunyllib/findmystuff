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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ahargunyllib.internraion.R
import com.ahargunyllib.internraion.ui.component.PrimaryButton
import com.ahargunyllib.internraion.ui.theme.Type
import com.ahargunyllib.internraion.ui.theme.White
import com.ahargunyllib.internraion.ui.theme.Yellow
import com.ahargunyllib.internraion.utils.Routes

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReceiptScreen(navController: NavController) {
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
                        text = "NOTA ELEKTRONIK",
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

                Card(
                    modifier = Modifier
                        .padding(bottom = 50.dp, start = 30.dp, end = 30.dp, top = 30.dp)
                        .fillMaxWidth()
                        .background(Color.Red, shape = RoundedCornerShape(15.dp)),
                    elevation = CardDefaults.cardElevation(10.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(White, shape = RoundedCornerShape(15.dp))
                            .verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        AsyncImage(
                            model = R.drawable.iv_report_logo,
                            contentDescription = "",
                            modifier = Modifier
                                .width(219.dp)
                                .height(90.dp)
                        )

                        Text(text = "Transaksi Sukses!", style = Type.receiptSuccess())

                        Text(text = "12 August 2023 10:00 GMT+7", style = Type.receiptTime())

                        TextField(
                            value = "Rp. 131.500",
                            textStyle = Type.receiptTotal(),
                            onValueChange = { },
                            modifier = Modifier
                                .fillMaxWidth(),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent
                            ),
                        )
                        HorizontalDivider(modifier = Modifier.padding(start = 8.dp, end = 8.dp))
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 12.dp)
                        ) {

                        }
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
                                        text = "Recipient",
                                        style = Type.paymentNoteStart(),
                                        modifier = Modifier.align(
                                            Alignment.Start
                                        )
                                    )
                                },
                                value = "Syifani",
                                textStyle = Type.paymentNoteEnd(),
                                onValueChange = { },
                                modifier = Modifier
                                    .fillMaxWidth(),
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
                                        text = "Bank Destination",
                                        style = Type.paymentNoteStart(),
                                        modifier = Modifier.align(
                                            Alignment.Start
                                        )
                                    )
                                },
                                value = "ShopeePay",
                                textStyle = Type.paymentNoteEnd(),
                                onValueChange = { },
                                modifier = Modifier
                                    .fillMaxWidth(),
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
                                        text = "Account Number",
                                        style = Type.paymentNoteStart(),
                                        modifier = Modifier.align(
                                            Alignment.Start
                                        )
                                    )
                                },
                                value = "8930462013",
                                textStyle = Type.paymentNoteEnd(),
                                onValueChange = { },
                                modifier = Modifier
                                    .fillMaxWidth(),
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
                                        text = "Transaction ID",
                                        style = Type.paymentNoteStart(),
                                        modifier = Modifier.align(
                                            Alignment.Start
                                        )
                                    )
                                },
                                value = "2435GASFD7523",
                                textStyle = Type.paymentNoteEnd(),
                                onValueChange = { },
                                modifier = Modifier
                                    .fillMaxWidth(),
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
                                        text = "Note",
                                        style = Type.paymentNoteStart(),
                                        modifier = Modifier.align(
                                            Alignment.Start
                                        )
                                    )
                                },
                                value = "Terima kasih sudah menemukan barang saya!",
                                textStyle = Type.paymentNoteEnd(),
                                onValueChange = { },
                                modifier = Modifier
                                    .fillMaxWidth(),
                                colors = TextFieldDefaults.textFieldColors(
                                    containerColor = Color.Transparent,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    disabledIndicatorColor = Color.Transparent
                                ),
                            )
                        }

                        HorizontalDivider(
                            modifier = Modifier.padding(
                                top = 18.dp,
                                bottom = 18.dp,
                                end = 8.dp,
                                start = 8.dp
                            )
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 35.dp, end = 35.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.Start
                            ) {
                                AsyncImage(
                                    model = R.drawable.ic_receipt_download,
                                    contentDescription = "",
                                    modifier = Modifier
                                        .width(18.dp)
                                        .height(18.dp)
                                )
                                Text(text = "Unduh", style = Type.receiptShare())
                            }

                            Row(
                                horizontalArrangement = Arrangement.End,
                            ) {
                                AsyncImage(
                                    model = R.drawable.ic_receipt_share,
                                    contentDescription = "",
                                    alignment = Alignment.CenterEnd,
                                    modifier = Modifier
                                        .width(17.dp)
                                        .height(18.dp)
                                )
                                Text(
                                    text = "Bagikan",
                                    style = Type.receiptShare(),
                                    textAlign = TextAlign.End
                                )
                            }
                        }

                        Spacer(modifier = Modifier.size(30.dp))

                        PrimaryButton(text = "HOME", onClick = { navController.navigate(Routes.RECEIPT) })
                    }
                }
            }
        }
    }
}
