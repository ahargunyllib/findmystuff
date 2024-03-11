package com.ahargunyllib.internraion.features.data.repository.user

import com.ahargunyllib.internraion.features.data.utils.Response
import kotlinx.coroutines.flow.Flow

interface IUserRepository {
    suspend fun signUpUser(email: String, password: String): Flow<Response>
    suspend fun signInUser(email: String, password: String): Flow<Response>
    suspend fun signOutUser()

    suspend fun getAccessToken(): String?
}