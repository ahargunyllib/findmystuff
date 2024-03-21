package com.ahargunyllib.internraion.features.presentation.screen.chat_list

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ahargunyllib.internraion.R
import com.ahargunyllib.internraion.features.data.network.SupabaseClient
import com.ahargunyllib.internraion.features.data.repository.chat_list.ChatListRepository
import com.ahargunyllib.internraion.features.presentation.navigation.BottomNavigationBar
import com.ahargunyllib.internraion.ui.component.CustomLoading
import com.ahargunyllib.internraion.ui.component.DoubleIconTopBar
import com.ahargunyllib.internraion.ui.theme.Type
import com.ahargunyllib.internraion.utils.Routes

@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun ChatListScreen(navController: NavController) {
    val viewModel = ChatListViewModel(chatListRepository = ChatListRepository(supabaseClient = SupabaseClient))
    val chatRoomsState = viewModel.chatRoomsState.collectAsState()

    Scaffold(
        topBar = { DoubleIconTopBar(title = "PESAN", navController = navController) },
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 32.dp, top = 72.dp, bottom = 96.dp, end = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            chatRoomsState.value.forEach { chatRoom ->
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.clickable {
                                navController.navigate("${Routes.CHAT_ROOM}/${chatRoom.chatRoom.chatRoomId}")
                            }
                        ) {
                            AsyncImage(
                                model = R.drawable.dummy_avatar,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(width = 64.dp, height = 64.dp)
                                    .clip(shape = RoundedCornerShape(100))
                                    .background(Color.Gray, shape = RoundedCornerShape(64.dp)),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.size(width = 8.dp, height = 8.dp))
                            Column {
                                Text(text = chatRoom.user.fullName, style = Type.textMedium())
                                Text(text = "", style = Type.textSmall())
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        HorizontalDivider()
                    }
                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }
    }
}

