package com.ahargunyllib.internraion.features.presentation.screen.home

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahargunyllib.internraion.utils.SharedPreferenceHelper
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
    var isUserLoggedIn by mutableStateOf(false)

    fun isUserLoggedIn(context: Context){
        viewModelScope.launch {
            val sharedPref = SharedPreferenceHelper(context)
            val accessToken = sharedPref.getStringData("accessToken")

            if (accessToken.isNullOrEmpty()) isUserLoggedIn = false
            else isUserLoggedIn = true
        }
    }
}