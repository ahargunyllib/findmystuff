package com.ahargunyllib.internraion.features.presentation.screen.profile

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahargunyllib.internraion.features.data.repository.user.UserRepository
import com.ahargunyllib.internraion.features.data.utils.ReportResponse
import com.ahargunyllib.internraion.features.data.utils.UserResponse
import com.ahargunyllib.internraion.utils.SharedPreferenceHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(private val userRepository: UserRepository): ViewModel() {
    private val _state = MutableStateFlow<UserResponse>(UserResponse.Loading)
    val state = _state.asStateFlow()

    init{
        getCurrentUser()
    }
    private fun getCurrentUser(){
        viewModelScope.launch {
            userRepository.getCurrentUser().collect {
                _state.value = it
            }
        }
    }

    fun signOutUser(context: Context){
        viewModelScope.launch {
            userRepository.signOutUser()
            SharedPreferenceHelper(context).clearPreferences()
        }
    }
}