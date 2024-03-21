package com.ahargunyllib.internraion.features.presentation.screen.chat_list.chat_room

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCard
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ahargunyllib.internraion.R
import com.ahargunyllib.internraion.features.data.network.SupabaseClient
import com.ahargunyllib.internraion.features.data.repository.chat_list.ChatListRepository
import com.ahargunyllib.internraion.features.domain.model.User
import com.ahargunyllib.internraion.ui.component.DoubleIconTopBar
import com.ahargunyllib.internraion.ui.theme.Type
import com.ahargunyllib.internraion.ui.theme.Yellow

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChatRoomScreen(navController: NavController, chatRoomId: String) {
    val viewModel = ChatRoomViewModel(
        chatListRepository = ChatListRepository(supabaseClient = SupabaseClient),
        chatRoomId
    )
    val userState = viewModel.userState.collectAsState()
    val messagesState = viewModel.messagesState.collectAsState()
    
    LaunchedEffect(Unit) {
        viewModel.realtimeDB(this)
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = { DoubleIconTopBar(title = "PESAN", navController = navController) },
        bottomBar = { ChatBar(viewModel = viewModel) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(64.dp))
            UserDescription(user = userState.value)
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                messagesState.value.forEach { m ->
                    item { BubbleChat(message = m.message!!, isMe = m.senderId != userState.value.userId)}
                }
                item {Spacer(modifier = Modifier.height(96.dp))}
            }
        }
    }
}

@Composable
fun UserDescription(user: User) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Yellow)
            .padding(vertical = 16.dp, horizontal = 8.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = R.drawable.dummy_avatar,
            contentDescription = null,
            modifier = Modifier
                .size(width = 64.dp, height = 64.dp)
                .clip(shape = RoundedCornerShape(100))
                .background(
                    Color.Gray, shape = RoundedCornerShape(64.dp)
                ),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.size(width = 8.dp, height = 8.dp))
        Column {
            Text(text = user.fullName, style = Type.textMedium())
            Text(text = user.email, style = Type.textSmall())
            Text(text = "Lokasi TBD", style = Type.textSmall())
        }
    }
}

@Composable
fun BubbleChat(message: String, isMe: Boolean) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Box(
            modifier = Modifier
                .align(if (isMe) Alignment.End else Alignment.Start)
                .clip(
                    RoundedCornerShape(
                        topStart = 48f,
                        topEnd = 48f,
                        bottomStart = if (isMe) 48f else 0f,
                        bottomEnd = if (isMe) 0f else 48f
                    )
                )
                .background(Color.LightGray)
                .padding(16.dp)
        ) {
            Text(text = message)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatBar(viewModel: ChatRoomViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .shadow(elevation = 1.dp)
            .padding(vertical = 16.dp, horizontal = 8.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .background(color = Yellow, shape = RoundedCornerShape(64.dp))
        ) {
            Icon(Icons.Filled.Add, contentDescription = "")
        }
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .background(color = Yellow, shape = RoundedCornerShape(64.dp))
        ) {
            Icon(Icons.Filled.AddCard, contentDescription = "Send")
        }
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .background(color = Yellow, shape = RoundedCornerShape(64.dp))
        ) {
            Icon(Icons.Filled.Call, contentDescription = "Send")
        }
        Spacer(modifier = Modifier.width(8.dp))
        TextField(
            value = viewModel.m.value,
            onValueChange = { viewModel.m.value = it },
            modifier = Modifier
                .weight(1f),
            shape = RoundedCornerShape(64.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Yellow,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            trailingIcon = {
                IconButton(
                    onClick = {
                        viewModel.sendMessage()
                        viewModel.m.value = ""
                    },
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                ) {
                    Icon(Icons.Outlined.Send, contentDescription = "Send")
                }
            },
        )
    }
}
