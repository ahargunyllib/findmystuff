package com.ahargunyllib.internraion.features.data.repository.user

import com.ahargunyllib.internraion.features.data.network.SupabaseClient
import com.ahargunyllib.internraion.features.data.utils.Response
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.gotrue.user.UserInfo
import io.github.jan.supabase.gotrue.user.UserSession
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow

class UserRepository(
    private val supabaseClient: SupabaseClient
) : IUserRepository {
    override suspend fun signUpUser(
        email: String,
        password: String,
    ): Flow<Response> = channelFlow<Response> {
        try {
            send(Response.Loading)
            supabaseClient.client.auth.signUpWith(Email) {
                this.email = email
                this.password = password
            }
            send(Response.Success("User signed"))
        } catch (e: Exception) {
            send(Response.Error(e.message ?: ""))
        }
    }

    override suspend fun signInUser(email: String, password: String): Flow<Response> =
        channelFlow<Response> {
            try {
                send(Response.Loading)
                supabaseClient.client.auth.signInWith(Email) {
                    this.email = email
                    this.password = password
                }
                send(Response.Success("User logged in"))
            } catch (e: Exception) {
                send(Response.Error(e.message ?: ""))
            }
        }

    override suspend fun signOutUser() {
        supabaseClient.client.auth.signOut()
    }

    override suspend fun getAccessToken(): String? {
        return supabaseClient.client.auth.currentSessionOrNull()?.accessToken
    }

    override suspend fun getRefreshToken(): String? {
        return supabaseClient.client.auth.currentSessionOrNull()?.refreshToken
    }

    override suspend fun getUserSession(): UserSession? {
        return supabaseClient.client.auth.currentSessionOrNull()
    }

    override suspend fun retrieveUser(accessToken: String): UserInfo {
        return supabaseClient.client.auth.retrieveUser(accessToken)
    }

    override suspend fun refreshSession(refreshToken: String): UserSession {
        return supabaseClient.client.auth.refreshSession(refreshToken = refreshToken)
    }

    override suspend fun importSession(userSession: UserSession) {
        supabaseClient.client.auth.importSession(userSession)
    }
}