package com.ahargunyllib.internraion.features.presentation.screen.auth

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahargunyllib.internraion.features.data.repository.user.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {
    val emailState = mutableStateOf("")
    val passwordState = mutableStateOf("")
    fun signInUser() {
        viewModelScope.launch {
            userRepository.signInUser(email = emailState.value, password = passwordState.value)
        }
    }
}