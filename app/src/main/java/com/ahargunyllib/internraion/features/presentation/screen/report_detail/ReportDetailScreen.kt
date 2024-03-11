package com.ahargunyllib.internraion.features.presentation.screen.report_detail

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ahargunyllib.internraion.features.data.network.SupabaseClient
import com.ahargunyllib.internraion.features.data.repository.report_detail.ReportDetailRepository

@Composable
fun ReportDetailScreen(navController: NavController, reportId: Int) {
    val viewModel = ReportDetailViewModel(
        reportDetailRepository = ReportDetailRepository(supabaseClient = SupabaseClient),
        reportId = reportId
    )

    Log.i("screen", "ReportDetailScreen: ${viewModel.report}")
    Column {
        Text(text = viewModel.report.name)
        Text(text = viewModel.report.note)
//        AsyncImage(
//            model = viewModel.imageUrl,
//            contentDescription = null,
//            modifier = Modifier
//                .size(width = 350.dp, height = 200.dp)
//                .background(Color.Gray, shape = RoundedCornerShape(12.dp)),
//            contentScale = ContentScale.Crop
//        )
    }

}