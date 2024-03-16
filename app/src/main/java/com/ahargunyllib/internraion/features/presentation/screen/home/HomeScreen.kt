package com.ahargunyllib.internraion.features.presentation.screen.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ahargunyllib.internraion.R
import com.ahargunyllib.internraion.features.data.network.SupabaseClient
import com.ahargunyllib.internraion.features.data.repository.user.UserRepository
import com.ahargunyllib.internraion.features.data.utils.Response
import com.ahargunyllib.internraion.features.presentation.navigation.BottomNavigationBar
import com.ahargunyllib.internraion.ui.theme.Green
import com.ahargunyllib.internraion.ui.theme.Type
import com.ahargunyllib.internraion.utils.Routes

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController) {
    val viewModel = HomeViewModel(userRepository = UserRepository(supabaseClient = SupabaseClient))
    val state = viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.isUserLoggedIn()
    }

    when (state.value) {
        is Response.Loading -> {}

        is Response.Success -> {}

        is Response.Error -> {
            navController.navigate(Routes.WELCOME)
        }
    }


    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            Column(
                modifier = Modifier.padding(top = 15.dp, bottom = 15.dp, end = 43.dp, start = 43.dp)
            ) {
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.iv_homepp_example),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .size(40.dp)
                            .clip(shape = RoundedCornerShape(100))
                    )
                    Column {
                        Text(text = "Rain Khaleed", style = Type.homeUsername())
                        Text(text = "Nama Barang: Tumbler Hijau", style = Type.homeCaption())
                        Text(
                            text = "Deskripsi: Terakhir kali saya memakai....",
                            style = Type.homeCaption()
                        )
                    }
                }
                Image(
                    painter = painterResource(id = R.drawable.iv_homestuff_example),
                    contentDescription = null,
                    Modifier
                        .width(273.dp)
                        .height(119.dp)
                )
                Row {
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(containerColor = Green),
                        modifier = Modifier
                            .width(63.dp)
                            .height(30.dp)
                    ) {
                        Text(text = "See more", style = Type.homeSeeMore())
                        Icon(
                            painter = painterResource(id = R.drawable.ic_home_seemore),
                            contentDescription = null,
                            modifier = Modifier.size(5.dp)
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        AsyncImage(
                            model = R.drawable.ic_home_message_notif,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.size(50.dp))

            Row {
                Text(text = "Home Screen")
                Button(onClick = { navController.navigate(Routes.MAPS) }) {
                    Text(text = "to maps")
                }
                Button(onClick = { navController.navigate("${Routes.REPORT}/0.0/0.0") }) {
                    Text(text = "to report")
                }
                Button(onClick = { navController.navigate(Routes.PROFILE) }) {
                    Text(text = "to profile")
                }
            }
        }

    }
}

