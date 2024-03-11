package com.ahargunyllib.internraion.features.presentation.screen.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ahargunyllib.internraion.R
import com.ahargunyllib.internraion.features.data.network.SupabaseClient
import com.ahargunyllib.internraion.features.data.repository.user.UserRepository
import com.ahargunyllib.internraion.ui.theme.Green
import com.ahargunyllib.internraion.ui.theme.Type
import com.ahargunyllib.internraion.ui.theme.White
import com.ahargunyllib.internraion.ui.theme.Yellow
import com.ahargunyllib.internraion.utils.Routes

@Composable
fun LoginScreen(navController: NavController) {
    val viewModel = LoginViewModel(userRepository = UserRepository(supabaseClient = SupabaseClient))
    val passwordVisible by rememberSaveable {
        mutableStateOf(false)
    }
    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Yellow),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back_button),
                contentDescription = "",
                modifier = Modifier
                    .size(45.dp)
                    .padding(start = 10.dp, top = 15.dp)
                    .clickable { navController.popBackStack() },
                tint = Green
            )

            Card(
                modifier = Modifier
                    .padding(bottom = 50.dp, start = 30.dp, end = 30.dp, top = 30.dp)
                    .fillMaxWidth()
                    .background(Color.Red, shape = RoundedCornerShape(15.dp)),
                elevation = CardDefaults.cardElevation(10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(White, shape = RoundedCornerShape(15.dp)),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.size(40.dp))

                    Image(
                        painter = painterResource(id = R.drawable.iv_logo_register),
                        contentDescription = "register logo",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(320.dp)
                            .height(140.dp)
                    )

                    Spacer(modifier = Modifier.size(12.dp))

                    TextField(
                        value = viewModel.emailState.value,
                        onValueChange = {
                            viewModel.emailState.value = it
                        },
                        label = {
                            Text(text = "Alamat Email")
                        }
                    )

                    Spacer(modifier = Modifier.size(10.dp))

                    TextField(
                        value = viewModel.passwordState.value,
                        onValueChange = {
                            viewModel.passwordState.value = it
                        },
                        label = {
                            Text(text = "Kata Sandi")
                        },
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password
                        )
                    )

                    Spacer(modifier = Modifier.size(4.dp))

                    Text(
                        text = "Lupa kata sandi?",
                        style = Type.textMedium(),
                        modifier = Modifier.padding(end = 175.dp),
                        textAlign = TextAlign.Left
                    )

                    Spacer(modifier = Modifier.size(80.dp))


                    Button(
                        onClick = {
                            viewModel.signInUser(context = context)
                            navController.navigate(Routes.HOME)
                        },
                        modifier = Modifier
                            .width(245.dp)
                            .height(54.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Green)
                    ) {
                        Text(
                            text = "Log in",
                            fontSize = 23.sp,
                            style = Type.displayLarge()
                        )
                    }

                    Spacer(modifier = Modifier.size(12.dp))

                    Text(
                        text = "Sign up dengan",
                        style = Type.textMedium(),
                        color = Color(0xFFBABABA)
                    )

                    Spacer(modifier = Modifier.size(15.dp))

                    Image(
                        painter = painterResource(id = R.drawable.ic_google_logo),
                        contentDescription = "google logo",
                        modifier = Modifier.size(50.dp)
                    )

                    Spacer(modifier = Modifier.size(15.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Tidak punya akun?", style = Type.textMedium())
                        TextButton(onClick = { navController.navigate(Routes.REGISTER) }) {
                            Text(text = "Sign up", style = Type.textMedium(), color = Yellow)
                        }
                    }



                }
            }
        }
    }
}


//    Surface(modifier = Modifier.fillMaxSize()) {
//        Column(
//            modifier = Modifier.fillMaxSize()
//        ) {
//            Button(onClick = { navController.popBackStack() }) {
//                Text("Back")
//            }
//            Text(text = "Login")
//
//            Column {
//
//            }
//            Spacer(modifier = Modifier.size(12.dp))
//
//            Column {
//
//            }
//
//
//
//
//
//
//        }
//    }
//
//}
