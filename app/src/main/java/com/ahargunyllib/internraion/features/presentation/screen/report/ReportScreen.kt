package com.ahargunyllib.internraion.features.presentation.screen.report

import android.annotation.SuppressLint
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ahargunyllib.internraion.R
import com.ahargunyllib.internraion.features.data.network.SupabaseClient
import com.ahargunyllib.internraion.features.data.repository.report.ReportRepository
import com.ahargunyllib.internraion.features.presentation.navigation.BottomNavigationBar
import com.ahargunyllib.internraion.ui.component.PrimaryButton
import com.ahargunyllib.internraion.ui.component.SingleIconTopBar
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

    Scaffold(
        topBar = { SingleIconTopBar(title = "LAPOR", navController = navController) },
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

            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
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

            Spacer(modifier = Modifier.size(16.dp))

            TextField(value = if (latitude == 0.0 || longitude == 0.0) "Tentukan Lokasi" else "TBD",
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = Color(0xFF6C6C6C),
                        shape = RoundedCornerShape(size = 16.dp)
                    )
                    .clip(shape = RoundedCornerShape(16.dp)),
                colors = TextFieldDefaults.textFieldColors(containerColor = Yellow),
                leadingIcon = {
                    Text(
                        "Lokasi: ",
                        modifier = Modifier.padding(start = 16.dp),
                        style = Type.reportSystem()
                    )
                },
                textStyle = Type.reportSystem(),
                enabled = false,
                trailingIcon = {
                    IconButton(onClick = { navController.navigate(Routes.LOCATION_PICKER) }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_report_iconlocation),
                            contentDescription = null,
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.size(8.dp))

            TextField(
                value = viewModel.nameState.value,
                onValueChange = { viewModel.nameState.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = Color(0xFF6C6C6C),
                        shape = RoundedCornerShape(size = 16.dp)
                    )
                    .clip(shape = RoundedCornerShape(16.dp)),
                colors = TextFieldDefaults.textFieldColors(containerColor = Yellow),
                leadingIcon = {
                    Text(
                        "Nama Barang: ",
                        modifier = Modifier.padding(start = 16.dp),
                        style = Type.reportSystem()
                    )
                },
                textStyle = Type.reportSystem(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences
                )
            )

            Spacer(modifier = Modifier.size(8.dp))

            TextField(
                value = viewModel.feeState.value,
                onValueChange = { viewModel.feeState.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = Color(0xFF6C6C6C),
                        shape = RoundedCornerShape(size = 16.dp)
                    )
                    .clip(shape = RoundedCornerShape(16.dp)),
                colors = TextFieldDefaults.textFieldColors(containerColor = Yellow),
                leadingIcon = {
                    Text(
                        "Bayaran: ",
                        modifier = Modifier.padding(start = 16.dp),
                        style = Type.reportSystem()
                    )
                },
                textStyle = Type.reportSystem(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.size(8.dp))

            TextField(
                value = viewModel.noteState.value,
                onValueChange = { viewModel.noteState.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = Color(0xFF6C6C6C),
                        shape = RoundedCornerShape(size = 16.dp)
                    )
                    .clip(shape = RoundedCornerShape(16.dp)),
                colors = TextFieldDefaults.textFieldColors(containerColor = Yellow),
                leadingIcon = {
                    Text(
                        "Deskripsi: ",
                        modifier = Modifier.padding(start = 16.dp),
                        style = Type.reportSystem()
                    )
                },
                textStyle = Type.reportSystem(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences
                )
            )

            Spacer(modifier = Modifier.size(16.dp))

            PrimaryButton(text = "UPLOAD", onClick = {
                viewModel.createReport(context, latitude, longitude)
                navController.navigate(Routes.HOME)
            })
        }
    }
}