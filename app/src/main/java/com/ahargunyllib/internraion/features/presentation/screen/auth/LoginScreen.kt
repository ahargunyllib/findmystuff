package com.ahargunyllib.internraion.features.presentation.screen.auth

import android.graphics.Paint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ahargunyllib.internraion.ui.theme.InternraionTheme

@Composable
fun LoginScreen(navController: NavController) {
    var username by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }
    var passwordVisible by rememberSaveable {
        mutableStateOf(false)
    }

    InternraionTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Button(onClick = { navController.popBackStack() }) {
                    Text("Back")
                }

                Text(text = "Login")

                Column {
                    TextField(
                        value = username,
                        onValueChange = {
                            username = it
                        },
                        label = {
                            Text(text = "Username")
                        }
                    )
                }

                Spacer(modifier = Modifier.size(12.dp))

                Column {
                    TextField(
                        value = password,
                        onValueChange = {
                            password = it
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

                Button(onClick = { /*TODO*/ }) {
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