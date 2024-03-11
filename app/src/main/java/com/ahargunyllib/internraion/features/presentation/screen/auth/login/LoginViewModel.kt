package com.ahargunyllib.internraion.features.presentation.screen.auth.login

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahargunyllib.internraion.features.data.repository.user.UserRepository
import com.ahargunyllib.internraion.utils.SharedPreferenceHelper
import kotlinx.coroutines.launch

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {
    val emailState = mutableStateOf("")
    val passwordState = mutableStateOf("")
    fun signInUser(context: Context){
        viewModelScope.launch {
            userRepository.signInUser(email = emailState.value, password = passwordState.value)

            val accessToken = userRepository.getAccessToken()
            val sharedPref = SharedPreferenceHelper(context)
            sharedPref.saveStringData("accessToken",accessToken)
        }
    }
}