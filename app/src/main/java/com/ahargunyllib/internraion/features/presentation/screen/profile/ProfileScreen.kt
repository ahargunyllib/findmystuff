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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.ahargunyllib.internraion.features.data.utils.UserResponse
import com.ahargunyllib.internraion.features.domain.model.User
import com.ahargunyllib.internraion.features.presentation.navigation.BottomNavigationBar
import com.ahargunyllib.internraion.ui.component.CustomLoading
import com.ahargunyllib.internraion.ui.component.SingleIconTopBar
import com.ahargunyllib.internraion.ui.theme.Green
import com.ahargunyllib.internraion.ui.theme.Type
import com.ahargunyllib.internraion.ui.theme.White
import com.ahargunyllib.internraion.ui.theme.Yellow
import com.ahargunyllib.internraion.utils.Routes

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(navController: NavController) {
    val viewModel =
        ProfileViewModel(userRepository = UserRepository(supabaseClient = SupabaseClient))
    val context = LocalContext.current
    val state = viewModel.state.collectAsState()

    Scaffold(
        topBar = { SingleIconTopBar(title = "PROFILE SAYA", navController = navController) },
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            item {
                Spacer(modifier = Modifier.size(64.dp))

                when (state.value) {
                    is UserResponse.Error -> {}
                    is UserResponse.Loading -> { CustomLoading() }
                    is UserResponse.Success -> {
                        UserDescription(user = (state.value as UserResponse.Success).user)
                    }
                }

                Spacer(modifier = Modifier.size(64.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                ) {
                    Text(
                        text = "AKUN",
                        style = Type.profileName(),
                        fontSize = 20.sp,
                        fontWeight = FontWeight(1000),
                    )
                    HorizontalDivider()
                    ProfileItem(model = R.drawable.ic_profile_wallet, text = "SAMBUNGKAN E-WALLET")
                    HorizontalDivider()
                    ProfileItem(model = R.drawable.ic_profile_akun, text = "AKUN SAYA")
                    HorizontalDivider()
                    ProfileItem(R.drawable.ic_profile_setting, "PENGATURAN")
                    HorizontalDivider()
                    ProfileItem(R.drawable.ic_profile_laporan, "LAPORAN")
                    HorizontalDivider()
                    ProfileItem(R.drawable.ic_profile_question, "BANTUAN")
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

@Composable
fun UserDescription(user: User) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Yellow)
            .padding(vertical = 16.dp, horizontal = 8.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = R.drawable.dummy_avatar,
            contentDescription = null,
            modifier = Modifier
                .size(width = 64.dp, height = 64.dp)
                .clip(shape = RoundedCornerShape(100))
                .background(
                    Color.Gray, shape = RoundedCornerShape(64.dp)
                ),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.size(width = 8.dp, height = 8.dp))
        Column {
            Text(text = user.fullName, style = Type.textMedium())
            Text(text = user.email, style = Type.textSmall())
            Text(text = "Lokasi TBD", style = Type.textSmall())
        }
    }
}

@Composable
fun ProfileItem(model: Any?, text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { },
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = model,
            contentDescription = null,
            modifier = Modifier
                .size(16.dp)
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(text = text, style = Type.profilOption())
    }
}