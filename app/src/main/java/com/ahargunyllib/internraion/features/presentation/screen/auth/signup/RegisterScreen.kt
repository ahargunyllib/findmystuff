package com.ahargunyllib.internraion.features.presentation.screen.auth.signup

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.platform.LocalContext
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
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(navController: NavController) {

    val viewModel =
        RegisterViewModel(userRepository = UserRepository(supabaseClient = SupabaseClient))
    val passwordVisible by rememberSaveable { mutableStateOf(false) }
    val confirmPasswordVisible by rememberSaveable { mutableStateOf(false) }
    val checkedState = remember { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }

    val scope = rememberCoroutineScope()

    val context = LocalContext.current

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
                        value = viewModel.emailState.value,
                        onValueChange = {
                            viewModel.emailState.value = it
                        },
                        label = {
                            Text(text = "Email Address")
                        }
                    )
                }
                Spacer(modifier = Modifier.size(12.dp))

                Column {
                    TextField(
                        value = viewModel.usernameState.value,
                        onValueChange = {
                            viewModel.usernameState.value = it
                        },
                        label = {
                            Text(text = "Username")
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

                Spacer(modifier = Modifier.size(12.dp))

                Column {
                    TextField(
                        value = viewModel.confirmPasswordState.value,
                        onValueChange = {
                            viewModel.confirmPasswordState.value = it
                        },
                        label = {
                            Text(text = "Confirm Password")
                        },
                        visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password
                        )
                    )
                }

                Text(text = "Sign up with")

                Button(onClick = {
                    if (checkedState.value) {
//                        viewModel.signUpUser()
                        navController.navigate("login")
                    } else {
                        Toast.makeText(context, "accept terms dan condition", Toast.LENGTH_SHORT).show()
                    }
                }) {
                    Text(text = "Sign up")
                }


                Row {
                    Checkbox(
                        checked = checkedState.value,
                        onCheckedChange = { checkedState.value = it }
                    )
                    TextButton(onClick = { /*TODO*/ }) {
                        Text(text = "Terms and Conditions")
                    }
                }

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    InternraionTheme {
        RegisterScreen(navController = rememberNavController())
    }
}