package com.ahargunyllib.internraion.features.presentation.screen.report_detail

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ahargunyllib.internraion.R
import com.ahargunyllib.internraion.features.data.network.SupabaseClient
import com.ahargunyllib.internraion.features.data.repository.report_detail.ReportDetailRepository
import com.ahargunyllib.internraion.features.data.utils.ReportResponse
import com.ahargunyllib.internraion.features.data.utils.Response
import com.ahargunyllib.internraion.features.domain.model.Report
import com.ahargunyllib.internraion.ui.theme.Green
import com.ahargunyllib.internraion.ui.theme.Type
import com.ahargunyllib.internraion.utils.Routes

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ReportDetailScreen(navController: NavController, reportId: Int) {
    val viewModel = ReportDetailViewModel(
        reportDetailRepository = ReportDetailRepository(supabaseClient = SupabaseClient),
        reportId = reportId
    )
    val state = viewModel.state.collectAsState()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .shadow(elevation = 4.dp)
                    .background(Color.White)
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back_button),
                    contentDescription = "",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { navController.navigate(Routes.WELCOME) },
                    tint = Green
                )
                Text(text = "POSTINGAN", style = Type.textMedium(), modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f), textAlign = TextAlign.Center)

            }
        },
    ) {
        when (state.value) {
            is ReportResponse.Loading -> {
                Log.i("REPORT RESPONSE LOADING", "ReportDetailScreen: LOADING")
            }

            is ReportResponse.Success -> {
                val report = (state.value as ReportResponse.Success).data
                Log.i("REPORT RESPONSE SUCCESS", "ReportDetailScreen: $report")
                Column {
                    Spacer(modifier = Modifier.height(64.dp))
                    Text(text = report.name)
                    Text(text = report.note)
                    AsyncImage(
                        model = viewModel.imageUrl,
                        contentDescription = null,
                        modifier = Modifier
                            .size(width = 350.dp, height = 200.dp)
                            .background(Color.Gray, shape = RoundedCornerShape(12.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Button(onClick = { navController.navigate("${Routes.CHAT_ROOM}/$reportId") }) {
                        Text("Chat")

                    }
                }
            }

            is ReportResponse.Error -> {
                Log.i(
                    "REPORT RESPONSE ERROR",
                    "ReportDetailScreen: ${(state.value as ReportResponse.Error).message}"
                )
                Toast.makeText(
                    context, (state.value as ReportResponse.Error).message, Toast.LENGTH_SHORT
                ).show()
                navController.popBackStack()
            }
        }

    }
}