package com.ahargunyllib.internraion.features.presentation.screen.welcome

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ahargunyllib.internraion.R
import com.ahargunyllib.internraion.ui.theme.Green
import com.ahargunyllib.internraion.ui.theme.Type
import com.ahargunyllib.internraion.ui.theme.Yellow
import com.ahargunyllib.internraion.utils.Routes

@Composable
fun WelcomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .width(314.dp)
                .height(317.dp),
            painter = painterResource(id = R.drawable.iv_logo_welcome),
            contentDescription = "welcome logo",
            contentScale = ContentScale.FillBounds
        )

        Spacer(modifier = Modifier.size(100.dp))

        Button(
            onClick = {
                navController.navigate(Routes.LOGIN)
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

        Spacer(modifier = Modifier.size(10.dp))

        OutlinedButton(
            onClick = {
                navController.navigate(Routes.REGISTER)
            },
            modifier = Modifier
                .width(245.dp)
                .height(54.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Yellow),
            border = BorderStroke(width = 1.dp, color = Green)
        ) {
            Text(
                text = "Sign up",
                fontSize = 23.sp,
                style = Type.displayLarge(),
                color = Green
            )
        }
    }

}

