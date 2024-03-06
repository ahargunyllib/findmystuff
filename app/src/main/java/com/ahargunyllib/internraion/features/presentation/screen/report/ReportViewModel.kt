package com.ahargunyllib.internraion.features.presentation.screen.report

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ReportViewModel(): ViewModel() {
    val nameState = mutableStateOf("")
    val noteState = mutableStateOf("")
}