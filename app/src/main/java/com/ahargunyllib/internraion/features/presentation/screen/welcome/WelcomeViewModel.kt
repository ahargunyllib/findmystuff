package com.ahargunyllib.internraion.features.presentation.screen.welcome

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahargunyllib.internraion.utils.SharedPreferenceHelper
import kotlinx.coroutines.launch

class WelcomeViewModel: ViewModel() {
    var isUserLoggedIn = false

    fun isUserLoggedIn(context: Context){
        viewModelScope.launch {
            val sharedPref = SharedPreferenceHelper(context)
            val accessToken = sharedPref.getStringData("accessToken")

            isUserLoggedIn = !accessToken.isNullOrEmpty()
        }
    }
}