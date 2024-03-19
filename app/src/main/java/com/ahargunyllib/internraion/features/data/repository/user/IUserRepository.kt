package com.ahargunyllib.internraion.features.data.repository.user

import com.ahargunyllib.internraion.features.data.utils.ReportItemResponse
import com.ahargunyllib.internraion.features.data.utils.Response
import io.github.jan.supabase.gotrue.user.UserInfo
import io.github.jan.supabase.gotrue.user.UserSession
import kotlinx.coroutines.flow.Flow

interface IUserRepository {
    suspend fun signUpUser(email: String, password: String, fullName: String): Flow<Response>
    suspend fun signInUser(email: String, password: String): Flow<Response>
    suspend fun signOutUser()
    suspend fun isUserLoggedIn(): Flow<Response>

    suspend fun getAccessToken(): String

    suspend fun getReportItems(): Flow<ReportItemResponse>
}