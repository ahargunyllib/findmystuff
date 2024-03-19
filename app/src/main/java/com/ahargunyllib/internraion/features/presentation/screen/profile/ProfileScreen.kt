package com.ahargunyllib.internraion.features.presentation.screen.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ahargunyllib.internraion.R
import com.ahargunyllib.internraion.features.data.network.SupabaseClient
import com.ahargunyllib.internraion.features.data.repository.user.UserRepository
import com.ahargunyllib.internraion.features.presentation.navigation.BottomNavigationBar
import com.ahargunyllib.internraion.ui.theme.Green
import com.ahargunyllib.internraion.ui.theme.Type
import com.ahargunyllib.internraion.ui.theme.White
import com.ahargunyllib.internraion.utils.Routes

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(navController: NavController) {
    val viewModel =
        ProfileViewModel(userRepository = UserRepository(supabaseClient = SupabaseClient))
    val context = LocalContext.current

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
                    text = "PROFIL SAYA",
                    style = Type.textMedium(),
                    fontSize = 17.sp,
                    fontWeight = FontWeight(600),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )

            }
        },
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 64.dp, bottom = 80.dp),
        ) {
            items(1) {
                Spacer(modifier = Modifier.size(53.dp))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(
                        start = 24.dp,
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
                            text = "Username.FullName",
                            style = Type.profileName()
                        )
                        Text(text = "username@gmail.com", style = Type.notifSecond())
                        Text(text = "Malang, Jawa Timur, Indonesia", style = Type.notifSecond())
                    }
                }

                Spacer(modifier = Modifier.size(59.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 26.dp, end = 24.dp),
                ) {
                    Text(
                        text = "AKUN",
                        style = Type.profileName(),
                        fontSize = 20.sp,
                        fontWeight = FontWeight(1000),
                    )
                    HorizontalDivider()
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 5.dp, top = 5.dp)
                            .clickable { },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            model = R.drawable.ic_profile_wallet,
                            contentDescription = null,
                            modifier = Modifier
                                .size(50.dp)
                                .padding(end = 13.dp)
                        )
                        Text(text = "SAMBUNGKAN E-WALLET", style = Type.profilOption())
                    }

                    HorizontalDivider()
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 5.dp, top = 5.dp)
                            .clickable { },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            model = R.drawable.ic_profile_akun,
                            contentDescription = null,
                            modifier = Modifier
                                .size(50.dp)
                                .padding(end = 24.dp, start = 8.dp)
                        )
                        Text(text = "AKUN SAYA", style = Type.profilOption())
                    }

                    HorizontalDivider()
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 5.dp, top = 5.dp)
                            .clickable { },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            model = R.drawable.ic_profile_setting,
                            contentDescription = null,
                            modifier = Modifier
                                .size(50.dp)
                                .padding(end = 24.dp, start = 8.dp)
                        )
                        Text(text = "PENGATURAN", style = Type.profilOption())
                    }

                    HorizontalDivider()
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 5.dp, top = 5.dp)
                            .clickable { },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            model = R.drawable.ic_profile_laporan,
                            contentDescription = null,
                            modifier = Modifier
                                .size(50.dp)
                                .padding(end = 24.dp, start = 8.dp)
                        )
                        Text(text = "LAPORAN", style = Type.profilOption())
                    }

                    HorizontalDivider()
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 5.dp, top = 5.dp)
                            .clickable { },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            model = R.drawable.ic_profile_question,
                            contentDescription = null,
                            modifier = Modifier
                                .size(50.dp)
                                .padding(end = 24.dp, start = 8.dp)
                        )
                        Text(text = "BANTUAN", style = Type.profilOption())
                    }

                    HorizontalDivider()

                    OutlinedButton(
                        onClick = {
                            viewModel.signOutUser(context = context)
                            navController.navigate(Routes.LOGIN)
                        }, colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFF5757)
                        ), modifier = Modifier.padding(top = 15.dp)
                    ) {
                        AsyncImage(
                            model = R.drawable.ic_profile_logout,
                            contentDescription = "",
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.size(4.dp))
                        Text(text = "Logout", style = Type.reportSystem(), color = White)
                    }

                }


            }


        }
    }
}
