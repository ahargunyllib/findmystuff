package com.ahargunyllib.internraion.features.presentation.screen.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ahargunyllib.internraion.ui.component.PrimaryButton
import com.ahargunyllib.internraion.ui.theme.Type
import com.ahargunyllib.internraion.utils.Routes

@Composable
fun SuccessSignUpScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 64.dp, vertical = 128.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "SELAMAT!",
                style = Type.successSigningText(),
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Akun Anda telah berhasil dibuat.",
                style = Type.successSigningText(),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

        PrimaryButton(text = "Lanjut", onClick = { navController.navigate(Routes.HOME) })
    }
}