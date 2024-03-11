package com.ahargunyllib.internraion.features.presentation.screen.profile

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahargunyllib.internraion.features.data.repository.user.UserRepository
import com.ahargunyllib.internraion.utils.SharedPreferenceHelper
import kotlinx.coroutines.launch

class ProfileViewModel(private val userRepository: UserRepository): ViewModel() {
    fun signOutUser(context: Context){
        viewModelScope.launch {
            userRepository.signOutUser()
            SharedPreferenceHelper(context).clearPreferences()
        }
    }
}