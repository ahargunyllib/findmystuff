package com.ahargunyllib.internraion.features.data.repository.report_detail

import android.util.Log
import com.ahargunyllib.internraion.features.data.network.SupabaseClient
import com.ahargunyllib.internraion.features.data.utils.ReportResponse
import com.ahargunyllib.internraion.features.data.utils.Response
import com.ahargunyllib.internraion.features.domain.model.ChatRoom
import com.ahargunyllib.internraion.features.domain.model.Report
import com.ahargunyllib.internraion.features.domain.model.User
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.storage.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.time.Duration.Companion.minutes


class ReportDetailRepository(private val supabaseClient: SupabaseClient): IReportDetailRepository {
    override suspend fun getReport(reportId: Int): Flow<ReportResponse> = flow {
        emit(ReportResponse.Loading)
        try {
            val report = supabaseClient.client.postgrest.from("reports").select{
                filter {
                    eq("report_id", reportId)
                }
            }.decodeSingle<Report>()
            val url = supabaseClient.client.storage.from("images").publicUrl("${report.name}.jpg")
            val user = supabaseClient.client.postgrest.from("users").select{
                filter {
                    eq("user_id", report.userId!!)
                }
            }.decodeSingle<User>()
            Log.i("REPORT DETAIL REPOS", "getReport success: $report $user $url")
            emit(ReportResponse.Success(report, user, url))
        } catch (e: Exception) {
            emit(ReportResponse.Error(e.message ?: ""))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun createChatRoom(reportId: Int): Flow<Response> = flow {
        emit(Response.Loading)
        try {
            val currentUserEmail = supabaseClient.client.auth.currentUserOrNull()?.email
            val currentUser = supabaseClient.client.postgrest.from("users").select{
                filter {
                    eq("email", currentUserEmail!!)
                }
            }.decodeSingle<User>()

            val report = supabaseClient.client.postgrest.from("reports").select {
                filter {
                    eq("report_id", reportId)
                }
            }.decodeSingle<Report>()

            // Cek apakah ngechat sendiri atau ngga wkwkwk
            if (currentUser.userId == report.userId){
                emit(Response.Error("Gak bisa chat sendiri!"))
                return@flow
            }

            // Cek apakah udah pernah buat chat room di report id tersebut
            val chatRoomExists = supabaseClient.client.postgrest.from("chat_rooms").select {
                filter {
                    eq("report_id", reportId)
                    eq("founder_id", currentUser.userId!!)
                }
            }.decodeSingleOrNull<ChatRoom>()
            if (chatRoomExists != null){
                emit(Response.Error("Udah pernah buat chat room nih!"))
                return@flow
            }


            val victimId = report.userId

            val chatRoom = ChatRoom(reportId = reportId, founderId = currentUser.userId, victimId = victimId)
            supabaseClient.client.postgrest.from("chat_rooms").insert(chatRoom)

            val chatRoomId = supabaseClient.client.postgrest.from("chat_rooms").select {
                filter {
                    eq("report_id", reportId)
                    eq("founder_id", currentUser.userId!!)
                }
            }.decodeSingle<ChatRoom>().chatRoomId

            emit(Response.Success("$chatRoomId"))
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: ""))
        }
    }.flowOn(Dispatchers.IO)
}