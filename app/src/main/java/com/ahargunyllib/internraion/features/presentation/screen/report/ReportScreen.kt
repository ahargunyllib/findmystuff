package com.ahargunyllib.internraion.features.presentation.screen.report

import android.annotation.SuppressLint
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ahargunyllib.internraion.features.data.network.SupabaseClient
import com.ahargunyllib.internraion.features.data.repository.report.ReportRepository
import com.ahargunyllib.internraion.utils.Routes
import com.ahargunyllib.internraion.utils.uriToByteArray

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportScreen(navController: NavController, latitude: Double, longitude: Double) {
    val viewModel = ReportViewModel(reportRepository = ReportRepository(supabaseClient = SupabaseClient))

    val context = LocalContext.current

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> viewModel.selectedImageUriState.value = uri }
    )

    Scaffold {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.align(Alignment.Start)
            ) {
                Text("Back")
            }

            Text(text = "Report Screen", fontSize = 25.sp)
            Spacer(modifier = Modifier.size(30.dp))

            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                item {
                    Button(onClick = {
                        singlePhotoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    }) {
                        Text (
                            "Pilih Foto"
                        )
                    }
                    AsyncImage(
                        model = viewModel.selectedImageUriState.value,
                        contentDescription = null,
                        modifier = Modifier.size(width = 350.dp, height = 200.dp).background(Color.Gray, shape = RoundedCornerShape(12.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
            }
            Spacer(modifier = Modifier.size(30.dp))

            // Barang Hilang
            Row(
                modifier = Modifier
                    .background(Color.Gray)
                    .size(width = 340.dp, height = 50.dp)
                    .padding(start = 15.dp, end = 15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Nama Barang:", fontSize = 20.sp)
                TextField(
                    value = viewModel.nameState.value,
                    onValueChange = { viewModel.nameState.value = it },
                    modifier = Modifier.background(Color.Transparent),
                    colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent)
                )
            }
            Spacer(modifier = Modifier.size(8.dp))

            // Catatan
            Row(
                modifier = Modifier
                    .background(Color.Gray)
                    .size(width = 340.dp, height = 50.dp)
                    .padding(start = 15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Catatan:", fontSize = 20.sp)
                TextField(
                    value = viewModel.noteState.value,
                    onValueChange = { viewModel.noteState.value = it },
                    modifier = Modifier.background(Color.Transparent),
                    colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent)
                )
            }

            Spacer(modifier = Modifier.size(20.dp))
            Button(onClick = { navController.navigate(Routes.LOCATION_PICKER) }) {
                Text(text = "to location picker")
            }
            Text(
                text = "Latitude: ${latitude}, Longitude: $longitude",
                modifier = Modifier.padding(16.dp)
            )

            // Upload Button
            Button(onClick = {
                viewModel.createReport(context, latitude, longitude)
                navController.navigate(Routes.HOME)
            }) {
                Text(
                    text = "Upload",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(start = 50.dp, end = 50.dp)
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReportScreenPreview() {
//    ReportScreen(navController = rememberNavController())

}