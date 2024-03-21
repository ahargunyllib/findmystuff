package com.ahargunyllib.internraion.features.presentation.screen.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ahargunyllib.internraion.R
import com.ahargunyllib.internraion.features.data.model.ReportItemModelResponse
import com.ahargunyllib.internraion.features.data.network.SupabaseClient
import com.ahargunyllib.internraion.features.data.repository.user.UserRepository
import com.ahargunyllib.internraion.features.data.utils.Response
import com.ahargunyllib.internraion.features.presentation.navigation.BottomNavigationBar
import com.ahargunyllib.internraion.ui.component.DoubleIconTopBar
import com.ahargunyllib.internraion.ui.theme.Green
import com.ahargunyllib.internraion.ui.theme.Type
import com.ahargunyllib.internraion.ui.theme.Yellow
import com.ahargunyllib.internraion.utils.Routes

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController) {
    val viewModel = HomeViewModel(userRepository = UserRepository(supabaseClient = SupabaseClient))
    val context = LocalContext.current
    val state = viewModel.state.collectAsState()
    val reportItemsState = viewModel.reportItemState.collectAsState()

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
        topBar = { DoubleIconTopBar(title = "HALAMAN", navController = navController) },
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 32.dp, top = 64.dp, bottom = 96.dp, end = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            reportItemsState.value.forEach { reportItem ->
                item {
                    ReportItem(reportItem = reportItem, navController = navController)
                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }

    }
}

@Composable
fun ReportItem(reportItem: ReportItemModelResponse, navController: NavController) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
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
                Text(
                    text = reportItem.user.fullName,
                    style = Type.textMedium()
                )
                Text(
                    text = reportItem.report.name, style = Type.textSmall()
                )
                Text(
                    text = reportItem.report.note,
                    style = Type.textSmall(),
                    maxLines = 1
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        AsyncImage(
            model = reportItem.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = Color(0xFF6C6C6C),
                    shape = RoundedCornerShape(size = 16.dp)
                )
                .clip(shape = RoundedCornerShape(16.dp))
                .height(200.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("${Routes.REPORT_DETAIL}/${reportItem.report.reportId}") },
            colors = ButtonDefaults.buttonColors(containerColor = Green),
        ) {
            Text(text = "See more", style = Type.homeSeeMore())
            Spacer(modifier = Modifier.size(8.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_home_seemore),
                contentDescription = null,
                tint = Color.Black
            )
        }
    }
}

