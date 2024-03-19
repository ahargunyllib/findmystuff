package com.ahargunyllib.internraion.features.presentation.screen.report

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahargunyllib.internraion.features.data.repository.report.ReportRepository
import com.ahargunyllib.internraion.features.data.utils.Response
import com.ahargunyllib.internraion.features.domain.model.Report
import com.ahargunyllib.internraion.utils.uriToByteArray
import kotlinx.coroutines.launch

class ReportViewModel(private val reportRepository: ReportRepository): ViewModel() {
    private val _state = mutableStateOf<Response>(Response.Loading)
    val state: State<Response> = _state

    val nameState = mutableStateOf("")
    val noteState = mutableStateOf("")
    val selectedImageUriState = mutableStateOf<Uri?>(null)
    val feeState = mutableStateOf("")

    private fun uploadFile(fileName: String, byteArray: ByteArray) {
        viewModelScope.launch {
            try {
                _state.value = Response.Loading
                reportRepository.uploadFile(fileName, byteArray)
                _state.value = Response.Success("File uploaded successfully!")
            } catch(e: Exception) {
                _state.value = Response.Error("Error: ${e.message}")
            }
        }
    }

    fun createReport(context: Context, latitude: Double, longitude: Double) {
        viewModelScope.launch {
            try {
                val userId = reportRepository.getCurrentUserId()

                reportRepository.createReport(
                    Report(
                        name = nameState.value,
                        note = noteState.value,
                        userId = userId,
                        fee = feeState.value.toDouble(),
                        latitude = latitude,
                        longitude = longitude,
                    )
                )

                val imageByteArray = selectedImageUriState.value?.uriToByteArray(context)
                imageByteArray?.let {
                    uploadFile(nameState.value, it)
                }

            } catch(e: Exception) {
                Log.i("CREATE REPORT FAILED", "createReport: ${e.message}")
                _state.value = Response.Error("Error: ${e.message}")
            }
        }
    }
}