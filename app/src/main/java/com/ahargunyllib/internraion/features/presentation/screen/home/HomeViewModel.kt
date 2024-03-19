package com.ahargunyllib.internraion.features.presentation.screen.home

import android.content.Context
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahargunyllib.internraion.features.data.model.ReportItemModelResponse
import com.ahargunyllib.internraion.features.data.repository.user.UserRepository
import com.ahargunyllib.internraion.features.data.utils.ReportItemResponse
import com.ahargunyllib.internraion.features.data.utils.Response
import com.ahargunyllib.internraion.utils.SharedPreferenceHelper
import io.github.jan.supabase.gotrue.user.UserInfo
import io.github.jan.supabase.gotrue.user.UserSession
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val userRepository: UserRepository): ViewModel() {
    private val _state = MutableStateFlow<Response>(Response.Loading)
    val state = _state.asStateFlow()

    private val _reportItemsState = MutableStateFlow(emptyList<ReportItemModelResponse>())
    val reportItemState: StateFlow<List<ReportItemModelResponse>> = _reportItemsState.asStateFlow()

    init {
        getReportItems()
        Log.i("HOME VIEW MODEL", "getting reportItems: ${reportItemState.value}")
    }

    fun isUserLoggedIn(){
        viewModelScope.launch {
            userRepository.isUserLoggedIn().collect {
                _state.value = it
            }
        }
    }

     private fun getReportItems(){
        viewModelScope.launch {
            userRepository.getReportItems().collect {
                when (it){
                    is ReportItemResponse.Success -> {
                        _reportItemsState.value = it.reportItems
                        Log.i("HOME VIEW MODEL", "getReportItems success: ${it.reportItems}")
                    }
                    is ReportItemResponse.Error -> {
//                        _state.value = Response.Error(it.message)
                        Log.i("HOME VIEW MODEL", "getReportItems error: ${it.message}")
                    }
                    is ReportItemResponse.Loading -> {
                        Log.i("HOME VIEW MODEL", "getReportItems: loading...")
                    }
                }
            }
        }
    }


}