package com.ahargunyllib.internraion.features.data.repository.user

import com.ahargunyllib.internraion.features.data.network.SupabaseClient
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email

class UserRepository (
    private val supabaseClient: SupabaseClient
): IUserRepository {
    override suspend fun signUpUser(
        email: String,
        password: String,
    ){
        supabaseClient.client.auth.signUpWith(Email){
            this.email = email
            this.password = password
        }
    }

    override suspend fun signInUser(email: String, password: String) {
        supabaseClient.client.auth.signInWith(Email){
            this.email = email
            this.password = password
        }
    }

    override suspend fun signOutUser() {
        supabaseClient.client.auth.signOut()
    }
}