package com.ahargunyllib.internraion.features.presentation.screen.report

import android.annotation.SuppressLint
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ahargunyllib.internraion.R
import com.ahargunyllib.internraion.features.data.network.SupabaseClient
import com.ahargunyllib.internraion.features.data.repository.report.ReportRepository
import com.ahargunyllib.internraion.features.presentation.navigation.BottomNavigationBar
import com.ahargunyllib.internraion.ui.theme.Green
import com.ahargunyllib.internraion.ui.theme.Type
import com.ahargunyllib.internraion.ui.theme.Yellow
import com.ahargunyllib.internraion.utils.Routes

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportScreen(navController: NavController, latitude: Double, longitude: Double) {
    val viewModel =
        ReportViewModel(reportRepository = ReportRepository(supabaseClient = SupabaseClient))

    val context = LocalContext.current

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> viewModel.selectedImageUriState.value = uri }
    )

    val scrollState = rememberScrollState()
    var offset by remember { mutableFloatStateOf(0f) }

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
                        .clickable { navController.popBackStack() },
                    tint = Green
                )
                Text(text = "LAPOR", style = Type.textMedium(), modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f), textAlign = TextAlign.Center)

            }
        },
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Spacer(modifier = Modifier.height(32.dp))
            AsyncImage(
                modifier = Modifier
                    .width(288.dp)
                    .height(103.dp)
                    .padding(top = 7.dp),
                model = R.drawable.iv_report_logo,
                contentDescription = null
            )

            Spacer(modifier = Modifier.size(30.dp))

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    AsyncImage(
                        model = viewModel.selectedImageUriState.value,
                        contentDescription = null,
                        modifier = Modifier
                            .size(width = 340.dp, height = 200.dp)
                            .background(Yellow)
                            .clickable {
                                singlePhotoPickerLauncher.launch(
                                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                )
                            },
                        contentScale = ContentScale.Crop
                    )
                }
            }
            Spacer(modifier = Modifier.size(15.dp))

            // Lokasi
            Row(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(50))
                    .border(width = 3.dp, color = Green)
                    .background(Yellow)
                    .size(width = 340.dp, height = 50.dp)
                    .padding(start = 15.dp, end = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = "Lokasi:",
                    style = Type.reportSystem()
                )
                TextButton(onClick = { navController.navigate(Routes.LOCATION_PICKER) }) {
                    AsyncImage(
                        modifier = Modifier
                            .padding(start = 20.dp, end = 3.dp)
                            .size(20.dp),
                        model = R.drawable.ic_report_iconlocation,
                        contentDescription = null
                    )
                    Text(text = "Masukkan lokasinya", style = Type.reportDetail())
                }
            }
            Spacer(modifier = Modifier.size(8.dp))

            // Nama Barang
            Row(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(50))
                    .border(width = 3.dp, color = Green)
                    .background(Yellow)
                    .size(width = 340.dp, height = 50.dp)
                    .padding(start = 15.dp, end = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = "Nama Barang:",
                    style = Type.reportSystem()
                )
                TextField(
                    value = viewModel.nameState.value,
                    onValueChange = { viewModel.nameState.value = it },
                    modifier = Modifier.background(Color.Transparent),
                    colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent)
                )
            }
            Spacer(modifier = Modifier.size(8.dp))

            // Bayaran
            Row(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(50))
                    .border(width = 3.dp, color = Green)
                    .background(Yellow)
                    .size(width = 340.dp, height = 50.dp)
                    .padding(start = 15.dp, end = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = "Bayaran:",
                    style = Type.reportSystem()
                )
//                TextField(
//                    value = viewModel.nameState.value,
//                    onValueChange = { viewModel.nameState.value = it },
//                    modifier = Modifier.background(Color.Transparent),
//                    colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent)
//                )
            }
            Spacer(modifier = Modifier.size(8.dp))

            // Note
            Row(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(20.dp))
                    .border(width = 3.dp, color = Green)
                    .background(Yellow)
                    .size(width = 340.dp, height = 150.dp)
                    .padding(start = 15.dp, end = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        modifier = Modifier.padding(start = 10.dp, top = 15.dp),
                        text = "Catatan:",
                        style = Type.reportSystem()
                    )
                    TextField(
                        value = viewModel.noteState.value,
                        textStyle = Type.reportDetail(),
                        onValueChange = { viewModel.noteState.value = it },
                        modifier = Modifier
                            .background(Color.Transparent)
                            .padding(start = 10.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        )
                    )

                }
            }

            Spacer(modifier = Modifier.size(20.dp))

            // Upload Button
            Button(
                onClick = {
                    viewModel.createReport(context, latitude, longitude)
                    navController.navigate(Routes.HOME)
                },
                modifier = Modifier
                    .width(245.dp)
                    .height(54.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Green)
            ) {
                Text(
                    text = "Upload",
                    fontSize = 23.sp,
                    style = Type.displayLarge()
                )
            }

        }
    }
}