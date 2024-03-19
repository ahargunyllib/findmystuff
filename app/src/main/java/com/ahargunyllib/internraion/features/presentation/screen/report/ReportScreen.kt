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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
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
                Text(
                    text = "LAPOR", style = Type.textMedium(), modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f), textAlign = TextAlign.Center
                )

            }
        },
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Spacer(modifier = Modifier.height(64.dp))
            AsyncImage(
                modifier = Modifier
                    .width(288.dp)
                    .height(103.dp)
                    .padding(top = 7.dp),
                model = R.drawable.iv_report_logo,
                contentDescription = null
            )

            Spacer(modifier = Modifier.size(16.dp))

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Box {
                        AsyncImage(
                            modifier = Modifier
                                .height(200.dp)
                                .background(Yellow, shape = RoundedCornerShape(16.dp))
                                .border(
                                    width = 1.dp,
                                    color = Color(0xFF6C6C6C),
                                    shape = RoundedCornerShape(size = 16.dp)
                                )
                                .clip(shape = RoundedCornerShape(16.dp))
                                .fillMaxWidth()
                                .clickable {
                                    singlePhotoPickerLauncher.launch(
                                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                    )
                                },
                            model = viewModel.selectedImageUriState.value,
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                        if (viewModel.selectedImageUriState.value == null) {
                            Column(
                                modifier = Modifier
                                    .height(200.dp)
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    "Masukkan Gambar",
                                    color = Color.Black,
                                    style = Type.reportSystem(),
                                )
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.size(16.dp))


            TextField(value = if (latitude == 0.0 || longitude == 0.0) "Tentukan Lokasi" else "TBD", onValueChange = {}, modifier = Modifier.fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = Color(0xFF6C6C6C),
                    shape = RoundedCornerShape(size = 16.dp)
                )
                .clip(shape = RoundedCornerShape(16.dp)),
                colors = TextFieldDefaults.textFieldColors(containerColor = Yellow),
                leadingIcon = {
                    Text("Lokasi: ", modifier= Modifier.padding(start = 16.dp), style = Type.reportSystem())
                },
                textStyle = Type.reportSystem(),
                enabled = false,
                trailingIcon= {
                    IconButton(onClick = { navController.navigate(Routes.LOCATION_PICKER) }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_report_iconlocation),
                            contentDescription = null,
                        )
                    }
                }
                )
            Spacer(modifier = Modifier.size(8.dp))

            TextField(value = viewModel.nameState.value, onValueChange = { viewModel.nameState.value = it }, modifier = Modifier.fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = Color(0xFF6C6C6C),
                    shape = RoundedCornerShape(size = 16.dp)
                )
                .clip(shape = RoundedCornerShape(16.dp)),
                colors = TextFieldDefaults.textFieldColors(containerColor = Yellow),
                leadingIcon = {
                    Text("Nama Barang: ", modifier= Modifier.padding(start = 16.dp), style = Type.reportSystem())
                },
                textStyle = Type.reportSystem(),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences
                )
            )
            Spacer(modifier = Modifier.size(8.dp))

            TextField(value = viewModel.feeState.value, onValueChange = { viewModel.feeState.value = it }, modifier = Modifier.fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = Color(0xFF6C6C6C),
                    shape = RoundedCornerShape(size = 16.dp)
                )
                .clip(shape = RoundedCornerShape(16.dp)),
                colors = TextFieldDefaults.textFieldColors(containerColor = Yellow),
                leadingIcon = {
                    Text("Bayaran: ", modifier= Modifier.padding(start = 16.dp), style = Type.reportSystem())
                },
                textStyle = Type.reportSystem(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            Spacer(modifier = Modifier.size(8.dp))

            TextField(value = viewModel.noteState.value, onValueChange = { viewModel.noteState.value = it }, modifier = Modifier.fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = Color(0xFF6C6C6C),
                    shape = RoundedCornerShape(size = 16.dp)
                )
                .clip(shape = RoundedCornerShape(16.dp)),
                colors = TextFieldDefaults.textFieldColors(containerColor = Yellow),
                leadingIcon = {
                    Text("Deskripsi: ", modifier= Modifier.padding(start = 16.dp), style = Type.reportSystem())
                },
                textStyle = Type.reportSystem(),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences
                )
                )
            Spacer(modifier = Modifier.size(16.dp))

            Button(
                onClick = {
                    viewModel.createReport(context, latitude, longitude)
                    navController.navigate(Routes.HOME)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Green)
            ) {
                Text(
                    text = "Upload",
                    fontSize = 24.sp,
                    style = Type.displayLarge()
                )
            }
        }
    }
}