package com.ahargunyllib.internraion.features.presentation.screen.auth.login

import android.widget.Toast
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.ahargunyllib.internraion.features.data.utils.Response
import com.ahargunyllib.internraion.ui.theme.Green
import com.ahargunyllib.internraion.ui.theme.Type
import com.ahargunyllib.internraion.ui.theme.White
import com.ahargunyllib.internraion.ui.theme.Yellow
import com.ahargunyllib.internraion.utils.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {
    val viewModel = LoginViewModel(userRepository = UserRepository(supabaseClient = SupabaseClient))
    var passwordVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val state = viewModel.state.collectAsState()

    when (state.value) {
        is Response.Loading -> {}

        is Response.Success -> {
            navController.navigate(Routes.HOME) {
                popUpTo(0)
            }
        }

        is Response.Error -> {
            Toast.makeText(context, (state.value as Response.Error).message, Toast.LENGTH_SHORT)
                .show()
        }
    }

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
                    .clickable { navController.navigate(Routes.WELCOME) },
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
                        .background(White, shape = RoundedCornerShape(15.dp))
                        .verticalScroll(rememberScrollState()),
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

                    Card(
                        elevation = CardDefaults.cardElevation(15.dp)
                    ) {
                        TextField(
                            value = viewModel.emailState.value,
                            onValueChange = {
                                viewModel.emailState.value = it
                            },
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = White,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent
                            ),
                            label = {
                                Text(
                                    text = "Alamat Email",
                                    style = Type.authenticationText()
                                )
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Email
                            )
                        )
                    }


                    Spacer(modifier = Modifier.size(10.dp))

                    Card(
                        elevation = CardDefaults.cardElevation(15.dp)
                    ) {
                        TextField(
                            value = viewModel.passwordState.value,
                            onValueChange = {
                                viewModel.passwordState.value = it
                            },
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = White,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent
                            ),
                            label = {
                                Text(
                                    text = "Kata Sandi",
                                    style = Type.authenticationText()
                                )
                            },
                            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password
                            ),
                            trailingIcon = {
                                val image = if (passwordVisible)
                                    Icons.Filled.Visibility
                                else Icons.Filled.VisibilityOff

                                // Localized description for accessibility services
                                val description =
                                    if (passwordVisible) "Hide password" else "Show password"

                                // Toggle button to hide or display password
                                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                    Icon(imageVector = image, description)
                                }
                            }
                        )
                    }

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
                        },
                        modifier = Modifier
                            .width(245.dp)
                            .height(54.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Green)
                    ) {
                        Text(
                            text = "Masuk",
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

                    Spacer(modifier = Modifier.size(20.dp))


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
