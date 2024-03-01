package com.ahargunyllib.internraion.features.presentation.screen.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ahargunyllib.internraion.features.data.network.SupabaseClient
import com.ahargunyllib.internraion.features.data.repository.user.UserRepository
import com.ahargunyllib.internraion.ui.theme.InternraionTheme

@Composable
fun LoginScreen(navController: NavController) {
    val viewModel = LoginViewModel(userRepository = UserRepository(supabaseClient = SupabaseClient))
    val passwordVisible by rememberSaveable {
        mutableStateOf(false)
    }

    InternraionTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxSize()
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.Center
            ) {
                Button(onClick = { navController.popBackStack() }) {
                    Text("Back")
                }
                Text(text = "Login")

                Column {
                    TextField(
                        value = viewModel.emailState.value,
                        onValueChange = {
                            viewModel.emailState.value = it
                        },
                        label = {
                            Text(text = "Email")
                        }
                    )
                }
                Spacer(modifier = Modifier.size(12.dp))

                Column {
                    TextField(
                        value = viewModel.passwordState.value,
                        onValueChange = {
                            viewModel.passwordState.value = it
                        },
                        label = {
                            Text(text = "Password")
                        },
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password
                        )
                    )
                }

                Text(text = "Forget Password?")

                Button(onClick = {
                    viewModel.signInUser()
                    navController.navigate("maps")
                }) {
                    Text(text = "Login")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Doesn't have an account?")
                    TextButton(onClick = { /*TODO*/ }) {
                        Text(text = "Sign up")
                    }
                }

            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    InternraionTheme {
        LoginScreen(navController = rememberNavController())
    }
}