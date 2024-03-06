package com.ahargunyllib.internraion.features.presentation.screen.report

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ahargunyllib.internraion.ui.theme.InternraionTheme
import java.util.Calendar
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportScreen(navController: NavController) {
    val viewModel = ReportViewModel()

    InternraionTheme {
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

            Row(
                modifier = Modifier
                    .size(width = 350.dp, height = 200.dp)
                    .background(Color.Gray, shape = RoundedCornerShape(12.dp))
            ) {

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

            // Upload Button
            Button(onClick = { /*TODO*/ }) {
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
    ReportScreen(navController = rememberNavController())

}