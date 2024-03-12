package com.ahargunyllib.internraion.features.data.repository.user

import com.ahargunyllib.internraion.features.data.utils.Response
import io.github.jan.supabase.gotrue.user.UserInfo
import io.github.jan.supabase.gotrue.user.UserSession
import kotlinx.coroutines.flow.Flow

interface IUserRepository {
    suspend fun signUpUser(email: String, password: String): Flow<Response>
    suspend fun signInUser(email: String, password: String): Flow<Response>
    suspend fun signOutUser()

    suspend fun getAccessToken(): String?
    suspend fun getRefreshToken(): String?

    suspend fun getUserSession(): UserSession?

    suspend fun retrieveUser(accessToken: String): UserInfo

    suspend fun refreshSession(refreshToken: String): UserSession

    suspend fun importSession(userSession: UserSession)
}