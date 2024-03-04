package com.ahargunyllib.internraion.features.presentation.screen.auth.register

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahargunyllib.internraion.features.data.repository.user.UserRepository
import kotlinx.coroutines.launch

class RegisterViewModel(private val userRepository: UserRepository) : ViewModel(){
    val emailState = mutableStateOf("")
    val usernameState = mutableStateOf("")
    val passwordState = mutableStateOf("")
    val confirmPasswordState = mutableStateOf("")
    fun signUpUser(){
        viewModelScope.launch{
            userRepository.signUpUser(email = emailState.value, password =  passwordState.value)
        }
    }
}