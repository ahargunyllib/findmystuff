package com.ahargunyllib.internraion.features.data.repository.user

import com.ahargunyllib.internraion.features.data.network.SupabaseClient
import com.ahargunyllib.internraion.features.data.utils.Response
import com.ahargunyllib.internraion.features.domain.model.User
import com.ahargunyllib.internraion.utils.SharedPreferenceHelper
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.gotrue.user.UserInfo
import io.github.jan.supabase.gotrue.user.UserSession
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow

class UserRepository(
    private val supabaseClient: SupabaseClient
) : IUserRepository {
    override suspend fun signUpUser(
        email: String,
        password: String,
        fullName: String,
    ): Flow<Response> = channelFlow<Response> {
        try {
            send(Response.Loading)
            supabaseClient.client.auth.signUpWith(Email) {
                this.email = email
                this.password = password
            }
            supabaseClient.client.postgrest.from("users").insert(User(fullName = fullName, email = email))
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

    override suspend fun isUserLoggedIn(): Flow<Response> = channelFlow {
        try {
            send(Response.Loading)
            val userSession = supabaseClient.client.auth.currentSessionOrNull()
            if (userSession != null) {
                send(Response.Success("User is logged in"))
            } else {
                send(Response.Error("User is not logged in"))
            }
        } catch (e: Exception) {
            send(Response.Error(e.message ?: ""))
        }
    }

    override suspend fun getAccessToken(): String {
        return supabaseClient.client.auth.currentSessionOrNull()?.accessToken ?: ""
    }
}