package com.ahargunyllib.internraion.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahargunyllib.internraion.ui.theme.Green
import com.ahargunyllib.internraion.ui.theme.Type
import com.ahargunyllib.internraion.ui.theme.Yellow
import com.ahargunyllib.internraion.utils.Routes

@Composable
fun PrimaryButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        modifier = Modifier
            .width(245.dp)
            .height(54.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Green)
    ) {
        Text(
            text = text,
            fontSize = 24.sp,
            style = Type.displayLarge()
        )
    }
}

@Composable
fun AccentButton(text: String, onClick: () -> Unit) {
    OutlinedButton(
        onClick = { onClick() },
        modifier = Modifier
            .width(245.dp)
            .height(54.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Yellow),
        border = BorderStroke(width = 1.dp, color = Green)
    ) {
        Text(
            text = text,
            fontSize = 24.sp,
            style = Type.displayLarge(),
            color = Green
        )
    }
}