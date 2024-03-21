package com.ahargunyllib.internraion.features.presentation.screen.chat_list.chat_room

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCard
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ahargunyllib.internraion.R
import com.ahargunyllib.internraion.features.data.network.SupabaseClient
import com.ahargunyllib.internraion.features.data.repository.chat_list.ChatListRepository
import com.ahargunyllib.internraion.ui.theme.Green
import com.ahargunyllib.internraion.ui.theme.Type
import com.ahargunyllib.internraion.ui.theme.White
import com.ahargunyllib.internraion.ui.theme.Yellow
import com.ahargunyllib.internraion.utils.Routes

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
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
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back_button),
                    contentDescription = "",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { navController.popBackStack() },
                    tint = Green
                )
                Text(
                    text = "PESAN", style = Type.textMedium(), modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f), textAlign = TextAlign.Center
                )
                IconButton(onClick = { navController.navigate(Routes.COMING_SOON) }) {
                    Icon(
                        Icons.Default.Person,
                        contentDescription = "",
                        modifier = Modifier
                            .size(24.dp),
                        tint = Green
                    )
                }

            }

        },
        bottomBar = {
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
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(64.dp))
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
                    Text(text = userState.value.fullName, style = Type.textMedium())
                    Text(text = userState.value.email, style = Type.textSmall())
                    Text(text = "Lokasi TBD", style = Type.textSmall())
                }
            }
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
