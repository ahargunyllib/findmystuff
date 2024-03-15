package com.ahargunyllib.internraion.features.presentation.screen.report_detail

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahargunyllib.internraion.features.data.repository.report.ReportRepository
import com.ahargunyllib.internraion.features.data.repository.report_detail.ReportDetailRepository
import com.ahargunyllib.internraion.features.domain.model.Report
import kotlinx.coroutines.launch

class ReportDetailViewModel(private val reportDetailRepository: ReportDetailRepository, reportId: Int) : ViewModel() {
    var report by mutableStateOf<Report?>(null)
    var imageUrl by mutableStateOf("")

    fun getReport(reportId: Int) {
        viewModelScope.launch {
            report = reportDetailRepository.getReport(reportId)
            Log.i("viewmodel", "getReport: $report")
        }
    }

    private fun readFile(){
        viewModelScope.launch {
            reportDetailRepository.readFile(fileName = report!!.name){
                imageUrl = "https://nsqyxpffpobtghztwlpq.supabase.co/storage/v1/$it"
            }
        }
        Log.i("viewmodel", "readFile: $imageUrl")
    }


}