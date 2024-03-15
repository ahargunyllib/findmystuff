package com.ahargunyllib.internraion.features.presentation.screen.home

import android.content.Context
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahargunyllib.internraion.features.data.repository.user.UserRepository
import com.ahargunyllib.internraion.features.data.utils.Response
import com.ahargunyllib.internraion.utils.SharedPreferenceHelper
import io.github.jan.supabase.gotrue.user.UserInfo
import io.github.jan.supabase.gotrue.user.UserSession
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val userRepository: UserRepository): ViewModel() {
    private val _state = MutableStateFlow<Response>(Response.Loading)
    val state = _state.asStateFlow()

    fun isUserLoggedIn(){
        viewModelScope.launch {
            userRepository.isUserLoggedIn().collect {
                _state.value = it
            }
        }
    }
}