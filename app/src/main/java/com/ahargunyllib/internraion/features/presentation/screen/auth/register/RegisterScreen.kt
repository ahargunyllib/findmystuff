package com.ahargunyllib.internraion.features.presentation.screen.auth.register

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ahargunyllib.internraion.R
import com.ahargunyllib.internraion.features.data.network.SupabaseClient
import com.ahargunyllib.internraion.features.data.repository.user.UserRepository
import com.ahargunyllib.internraion.features.data.utils.Response
import com.ahargunyllib.internraion.ui.component.AccentButton
import com.ahargunyllib.internraion.ui.component.AuthLayout
import com.ahargunyllib.internraion.ui.theme.Green
import com.ahargunyllib.internraion.ui.theme.Grey
import com.ahargunyllib.internraion.ui.theme.Type
import com.ahargunyllib.internraion.ui.theme.White
import com.ahargunyllib.internraion.ui.theme.Yellow
import com.ahargunyllib.internraion.utils.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController) {

    val viewModel =
        RegisterViewModel(userRepository = UserRepository(supabaseClient = SupabaseClient))
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val state = viewModel.state.collectAsState()

    when (state.value) {
        is Response.Loading -> {}

        is Response.Success -> {
            navController.navigate(Routes.SUCCESS_SIGN_UP) {
                popUpTo(0)
            }
        }

        is Response.Error -> {
            Toast.makeText(context, (state.value as Response.Error).message, Toast.LENGTH_SHORT)
                .show()
        }
    }

    AuthLayout(
        accentColor = Green,
        primaryColor = Yellow,
        navController = navController,
    ) {
        Card(
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
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
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                )
            )
        }

        Spacer(modifier = Modifier.size(8.dp))

        Card(
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.usernameState.value,
                onValueChange = {
                    viewModel.usernameState.value = it
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                label = {
                    Text(text = "Nama Pengguna", style = Type.authenticationText())
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences
                )
            )
        }

        Spacer(modifier = Modifier.size(8.dp))

        Card(
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
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
                    Text(text = "Kata Sandi", style = Type.authenticationText())
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                trailingIcon = {
                    val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    val description = if (passwordVisible) "Hide password" else "Show password"

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, description)
                    }
                }
            )
        }

        Spacer(modifier = Modifier.size(8.dp))

        Card(
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.confirmPasswordState.value,
                onValueChange = {
                    viewModel.confirmPasswordState.value = it
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                label = {
                    Text(
                        text = "Konfirmasi Kata Sandi",
                        style = Type.authenticationText()
                    )
                },
                visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                trailingIcon = {
                    val image = if (confirmPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    val description = if (confirmPasswordVisible) "Hide password" else "Show password"

                    IconButton(onClick = {
                        confirmPasswordVisible = !confirmPasswordVisible
                    }) {
                        Icon(imageVector = image, description)
                    }
                }
            )
        }

        Spacer(modifier = Modifier.size(32.dp))

        AccentButton(text = "Daftar", onClick = { viewModel.signUpUser() })

        Spacer(modifier = Modifier.size(8.dp))

        Text(
            text = "Sign up dengan",
            style = Type.textMedium(),
            color = Grey
        )

        Spacer(modifier = Modifier.size(8.dp))

        Image(
            painter = painterResource(id = R.drawable.ic_google_logo),
            contentDescription = "google logo",
            modifier = Modifier.size(48.dp)
        )

        Spacer(modifier = Modifier.size(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Sudah punya akun?", style = Type.textMedium())
            TextButton(onClick = { navController.navigate(Routes.LOGIN) }) {
                Text(text = "Login up", style = Type.textMedium(), color = Yellow)
            }
        }
    }
}

