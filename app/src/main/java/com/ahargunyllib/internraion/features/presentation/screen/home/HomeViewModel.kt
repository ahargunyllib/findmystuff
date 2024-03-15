package com.ahargunyllib.internraion.features.presentation.screen.home

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahargunyllib.internraion.features.data.repository.user.UserRepository
import com.ahargunyllib.internraion.utils.SharedPreferenceHelper
import io.github.jan.supabase.gotrue.user.UserInfo
import io.github.jan.supabase.gotrue.user.UserSession
import kotlinx.coroutines.launch

class HomeViewModel(private val userRepository: UserRepository): ViewModel() {
    var isUserLoggedIn by mutableStateOf(false)

    fun isUserLoggedIn(context: Context){
        viewModelScope.launch {
            val sharedPref = SharedPreferenceHelper(context)
            var accessToken = sharedPref.getStringData("accessToken")
            var refreshToken = sharedPref.getStringData("refreshToken")

            if (accessToken.isNullOrEmpty() ||  refreshToken.isNullOrEmpty()) isUserLoggedIn = false
            else {
                isUserLoggedIn = try {
                    val user = userRepository.retrieveUser(accessToken)
                    val userSession = UserSession(accessToken = accessToken, refreshToken = refreshToken, expiresIn = 60, tokenType = "Bearer", user = user)
                    userRepository.importSession(userSession)

                    accessToken = userRepository.getAccessToken()
                    refreshToken = userRepository.getRefreshToken()

                    sharedPref.saveStringData("accessToken", accessToken)
                    sharedPref.saveStringData("refreshToken", refreshToken)
                    true
                } catch (e: Exception){
                    Log.i("home view model", "isUserLoggedIn: $e")
                    false
                }
            }
        }
    }


}