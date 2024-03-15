package com.ahargunyllib.internraion.features.presentation.screen.auth.login

import android.content.Context
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahargunyllib.internraion.features.data.repository.user.UserRepository
import com.ahargunyllib.internraion.features.data.utils.Response
import com.ahargunyllib.internraion.utils.SharedPreferenceHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {
    val emailState = mutableStateOf("")
    val passwordState = mutableStateOf("")

    private val _state = MutableStateFlow<Response>(Response.Loading)
    val state = _state.asStateFlow()

    fun signInUser(context: Context) {
        viewModelScope.launch {
            userRepository.signInUser(emailState.value, passwordState.value).collect {
                _state.value = it
            }
            if (state.value is Response.Success) {
                val accessToken = userRepository.getAccessToken()
                val sharedPref = SharedPreferenceHelper(context)
                sharedPref.saveStringData("accessToken", accessToken)
            }
        }
    }
}