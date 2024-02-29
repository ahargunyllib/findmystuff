package com.ahargunyllib.internraion.features.presentation.screen.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ahargunyllib.internraion.ui.theme.InternraionTheme

@Composable
fun RegisterScreen(navController: NavController) {
    var emailAdress by rememberSaveable {
        mutableStateOf("")
    }
    var username by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }
    var passwordVisible by rememberSaveable {
        mutableStateOf(false)
    }
    var confirmPassword by rememberSaveable {
        mutableStateOf("")
    }
    var confirmPassVisible by rememberSaveable {
        mutableStateOf(false)
    }
    val checked = remember {
        mutableStateOf(false)
    }

    InternraionTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                Button(onClick = { navController.popBackStack() }) {
                    Text("Back")
                }
                Text(text = "Register")
                Column {
                    TextField(
                        value = emailAdress,
                        onValueChange = {
                            emailAdress = it
                        },
                        label = {
                            Text(text = "Email Address")
                        }
                    )
                }
                Spacer(modifier = Modifier.size(12.dp))
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
                Spacer(modifier = Modifier.size(12.dp))
                Column {
                    TextField(
                        value = confirmPassword,
                        onValueChange = {
                            confirmPassword = it
                        },
                        label = {
                            Text(text = "Confirm Password")
                        },
                        visualTransformation = if (confirmPassVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password
                        )
                    )
                }
                Text(text = "Sign up with")
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Sign up")
                }
                Row {
                    Checkbox(checked = checked.value, onCheckedChange = { checked.value = it })
                    TextButton(onClick = { /*TODO*/ }) {
                        Text(text = "Terms and Conditions")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun RegisterScreenPreview() {
    InternraionTheme {
        RegisterScreen(navController = rememberNavController())
    }
}