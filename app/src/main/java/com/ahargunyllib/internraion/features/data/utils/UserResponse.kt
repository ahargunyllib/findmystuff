package com.ahargunyllib.internraion.features.data.utils

import com.ahargunyllib.internraion.features.domain.model.Report
import com.ahargunyllib.internraion.features.domain.model.User

sealed class UserResponse {
    data object Loading : UserResponse()
    data class Success(val user: User) : UserResponse()
    data class Error(val message: String) : UserResponse()
}