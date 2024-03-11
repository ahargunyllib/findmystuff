package com.ahargunyllib.internraion.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ahargunyllib.internraion.R


object Type{
    fun displayLarge() = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_bold)),
        fontSize = 57.sp,
        lineHeight = 64.sp
    )

    fun displayMedium() = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_medium)),
        fontSize = 45.sp,
        lineHeight = 52.sp
    )

    fun displaySmall() = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_light)),
        fontSize = 36.sp,
        lineHeight = 44.sp
    )

    fun textLarge() = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_bold)),
        fontSize = 20.sp,
//        lineHeight = 64.sp
    )

    fun textMedium() = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_medium)),
        fontSize = 15.sp,
//        lineHeight = 52.sp
    )

    fun textSmall() = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_light)),
        fontSize = 12.sp,
//        lineHeight = 44.sp
    )

    fun successSigningText() = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_medium)),
        fontSize = 35.sp,
        fontWeight = FontWeight(600),
        color = Green
//        lineHeight = 100.sp
    )


}
