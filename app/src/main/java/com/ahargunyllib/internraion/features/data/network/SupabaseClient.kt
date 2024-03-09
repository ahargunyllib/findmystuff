package com.ahargunyllib.internraion.features.data.network

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage

object SupabaseClient {
    val client = createSupabaseClient(
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im5zcXl4cGZmcG9idGdoenR3bHBxIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MDkxNzY0MTEsImV4cCI6MjAyNDc1MjQxMX0.Cm5-7X1MCKr6GKU8WWXh3z9XTj8d4O7jIFhkk3vi71s",
        supabaseUrl = "https://nsqyxpffpobtghztwlpq.supabase.co"
    ) {
        install(Auth) {
            alwaysAutoRefresh = false
            autoLoadFromStorage = false
        }
        install(Storage)
        install(Postgrest)
    }
}