package com.ahargunyllib.internraion.features.data.repository.user

interface IUserRepository {
    suspend fun signUpUser(email: String, password: String)
    suspend fun signInUser(email: String, password: String)
    suspend fun signOutUser()

    suspend fun getAccessToken(): String?
}