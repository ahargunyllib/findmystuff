package com.ahargunyllib.internraion.features.presentation.screen.report

import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahargunyllib.internraion.features.data.model.UserState
import com.ahargunyllib.internraion.features.data.repository.report.ReportRepository
import com.ahargunyllib.internraion.features.domain.model.Report
import kotlinx.coroutines.launch

class ReportViewModel(private val reportRepository: ReportRepository): ViewModel() {
    private val _userState = mutableStateOf<UserState>(UserState.Loading)
    val userState: State<UserState> = _userState

    val nameState = mutableStateOf("")
    val noteState = mutableStateOf("")
    val selectedImageUriState = mutableStateOf<Uri?>(null)

    fun uploadFile(fileName: String, byteArray: ByteArray) {
        viewModelScope.launch {
            try {
                _userState.value = UserState.Loading
                reportRepository.uploadFile(fileName, byteArray)
                _userState.value = UserState.Success("File uploaded successfully!")
            } catch(e: Exception) {
                _userState.value = UserState.Error("Error: ${e.message}")
            }
        }
    }

    fun createReport(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            try {
                _userState.value = UserState.Loading
                reportRepository.createReport(
                    Report(
                        name = nameState.value,
                        note = noteState.value,
                        userId = 1,
                        fee = 0.0,
                        latitude = latitude,
                        longitude = longitude,
                    )
                )
                _userState.value = UserState.Success("Report created successfully!")
            } catch(e: Exception) {
                _userState.value = UserState.Error("Error: ${e.message}")
            }
        }
    }
}