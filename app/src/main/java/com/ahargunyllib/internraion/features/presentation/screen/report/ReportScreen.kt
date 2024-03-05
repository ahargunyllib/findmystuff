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

    //TANGGAL
    // Fetching the Local Context
    val mContext = LocalContext.current

    // Declaring integer values
    // for year, month and day
    val mYear: Int
    val mMonth: Int
    val mDay: Int

    // Initializing a Calendar
    val mCalendar = Calendar.getInstance()

    // Fetching current year, month and day
    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    // Declaring a string value to
    // store date in string format
    val mDate = remember { mutableStateOf("") }

    // Declaring DatePickerDialog and setting
    // initial values as current values (present year, month and day)
    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            mDate.value = "$mDayOfMonth/${mMonth + 1}/$mYear"
        }, mYear, mMonth, mDay
    )

    //WAKTU
    // Fetching local context
//    val mContext = LocalContext.current

    // Declaring and initializing a calendar
//    val mCalendar = Calendar.getInstance()
    val mHour = mCalendar[Calendar.HOUR_OF_DAY]
    val mMinute = mCalendar[Calendar.MINUTE]

    // Value for storing time as a string
    val mTime = remember { mutableStateOf("") }

    // Creating a TimePicker dialod
    val mTimePickerDialog = TimePickerDialog(
        mContext,
        { _, mHour: Int, mMinute: Int ->
            mTime.value = "$mHour:$mMinute"
        }, mHour, mMinute, false
    )

    //BARANG HILANG
    var barangHilang by rememberSaveable {
        mutableStateOf("")
    }

    //CATATAN
    var catatan by rememberSaveable {
        mutableStateOf("")
    }

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

            // Tanggal
            Row(
                modifier = Modifier
                    .background(Color.Gray)
                    .size(width = 340.dp, height = 50.dp)
                    .padding(start = 15.dp),

                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Tanggal:", fontSize = 20.sp)

                Text(text = "${mDate.value}", fontSize = 20.sp, color = Color.White)

                Button(onClick = {
                    mDatePickerDialog.show()
                }, modifier = Modifier.offset(x = 50.dp)) {
                    Image(imageVector = Icons.Default.CalendarMonth, contentDescription = null)
                }

            }
            Spacer(modifier = Modifier.size(8.dp))

            // Waktu
            Row(
                modifier = Modifier
                    .background(Color.Gray)
                    .size(width = 340.dp, height = 50.dp)
                    .padding(start = 15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Waktu:", fontSize = 20.sp)
                Text(text = "${mTime.value}", fontSize = 20.sp, color = Color.White)
                Button(
                    onClick = { mTimePickerDialog.show() },
                    modifier = Modifier.offset(x = 50.dp)
                ) {
                    Image(imageVector = Icons.Default.AccessTime, contentDescription = null)
                }
            }
            Spacer(modifier = Modifier.size(8.dp))

            // Lokasi
            Row(
                modifier = Modifier
                    .background(Color.Gray)
                    .size(width = 340.dp, height = 50.dp)
                    .padding(start = 15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Lokasi:", fontSize = 20.sp)
                Image(imageVector = Icons.Default.LocationOn, contentDescription = null)
            }
            Spacer(modifier = Modifier.size(8.dp))

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
                    value = barangHilang,
                    onValueChange = { barangHilang = it },
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
                    value = catatan,
                    onValueChange = { catatan = it },
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