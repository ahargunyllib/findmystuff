package com.ahargunyllib.internraion.features.presentation.screen.auth.register

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahargunyllib.internraion.features.data.repository.user.UserRepository
import com.ahargunyllib.internraion.features.data.utils.Response
import com.ahargunyllib.internraion.utils.SharedPreferenceHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterViewModel(private val userRepository: UserRepository) : ViewModel(){
    val emailState = mutableStateOf("")
    val usernameState = mutableStateOf("")
    val passwordState = mutableStateOf("")
    val confirmPasswordState = mutableStateOf("")

    private val _state = MutableStateFlow<Response>(Response.Loading)
    val state = _state.asStateFlow()

    fun signUpUser(){
        viewModelScope.launch{
            userRepository.signUpUser(email = emailState.value, password = passwordState.value, fullName = usernameState.value).collect {
                _state.value = it
            }
        }
    }
}

