package com.ahargunyllib.internraion.features.presentation.screen.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ahargunyllib.internraion.R
import com.ahargunyllib.internraion.ui.component.AccentButton
import com.ahargunyllib.internraion.ui.component.PrimaryButton
import com.ahargunyllib.internraion.utils.Routes

@Composable
fun WelcomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 128.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(),
            painter = painterResource(id = R.drawable.iv_logo_welcome),
            contentDescription = "welcome logo",
            contentScale = ContentScale.Crop
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            PrimaryButton(text = "Masuk", onClick = { navController.navigate(Routes.LOGIN)})
            Spacer(modifier = Modifier.size(8.dp))
            AccentButton(text = "Daftar", onClick = { navController.navigate(Routes.REGISTER) })
        }
    }
}

