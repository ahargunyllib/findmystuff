package com.ahargunyllib.internraion.features.presentation.screen.auth.register

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahargunyllib.internraion.features.data.repository.user.UserRepository
import com.ahargunyllib.internraion.utils.SharedPreferenceHelper
import kotlinx.coroutines.launch

class RegisterViewModel(private val userRepository: UserRepository) : ViewModel(){
    val emailState = mutableStateOf("")
    val usernameState = mutableStateOf("")
    val passwordState = mutableStateOf("")
    val confirmPasswordState = mutableStateOf("")

    fun signUpUser(context: Context){
        viewModelScope.launch{
            userRepository.signUpUser(email = emailState.value, password =  passwordState.value)

            val accessToken = userRepository.getAccessToken()
            val sharedPref = SharedPreferenceHelper(context)
            sharedPref.saveStringData("accessToken",accessToken)
        }
    }
}

