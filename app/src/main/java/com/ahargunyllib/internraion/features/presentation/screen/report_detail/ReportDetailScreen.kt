package com.ahargunyllib.internraion.features.presentation.screen.report_detail

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ahargunyllib.internraion.features.data.network.SupabaseClient
import com.ahargunyllib.internraion.features.data.repository.report_detail.ReportDetailRepository
import com.ahargunyllib.internraion.features.data.utils.ReportResponse
import com.ahargunyllib.internraion.features.data.utils.Response
import com.ahargunyllib.internraion.features.domain.model.Report
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

    Scaffold {
        when (state.value) {
            is ReportResponse.Loading -> {
                Log.i("REPORT RESPONSE LOADING", "ReportDetailScreen: LOADING")
            }

            is ReportResponse.Success -> {
                val report = (state.value as ReportResponse.Success).data
                Log.i("REPORT RESPONSE SUCCESS", "ReportDetailScreen: $report")
                Column {
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