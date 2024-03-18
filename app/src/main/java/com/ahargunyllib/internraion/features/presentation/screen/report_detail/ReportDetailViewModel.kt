package com.ahargunyllib.internraion.features.presentation.screen.report_detail

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahargunyllib.internraion.features.data.repository.report_detail.ReportDetailRepository
import com.ahargunyllib.internraion.features.data.utils.ReportResponse
import com.ahargunyllib.internraion.features.domain.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ReportDetailViewModel(private val reportDetailRepository: ReportDetailRepository, private val reportId: Int) : ViewModel() {
    private val _state = MutableStateFlow<ReportResponse>(ReportResponse.Loading)
    val state = _state.asStateFlow()

    init {
        getReport(reportId)
        Log.i("REPORT VIEW MODEL", "getting report: ${state.value}")
    }

    private fun getReport(reportId: Int) {
        viewModelScope.launch {
            reportDetailRepository.getReport(reportId).collect {
                _state.value = it
                Log.i("REPORT DETAIL VM", "getReport: ${it}")
            }
        }
    }
}