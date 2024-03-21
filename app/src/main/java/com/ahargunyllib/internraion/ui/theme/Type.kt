package com.ahargunyllib.internraion.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
        fontSize = 33.sp,
        fontWeight = FontWeight(600),
        color = Green
//        lineHeight = 100.sp
    )

    fun authenticationText() = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_medium)),
        fontSize = 15.sp,
        fontWeight = FontWeight(700),
        color = Grey,
        textAlign = TextAlign.Center
    )

    fun homeUsername() = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_medium)),
        fontSize = 17.sp,
        fontWeight = FontWeight(600),
        color = Black,
    )
    fun homeCaption() = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_medium)),
        fontSize = 10.sp,
        fontWeight = FontWeight(400),
        color = Grey,
    )

    fun homeSeeMore() = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_medium)),
        fontSize = 9.sp,
        fontWeight = FontWeight(500),
        color = Black,
    )

    fun reportSystem() = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_medium)),
        fontSize = 15.sp,
        fontWeight = FontWeight(700),
        color = Black,
    )

    fun reportDetail() = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_medium)),
        fontSize = 17.sp,
        fontWeight = FontWeight(600),
        color = White,
    )

    fun notifFirst() = TextStyle(
        fontFamily = FontFamily(Font(R.font.inter_medium)),
        fontSize = 14.sp,
        textAlign = TextAlign.Justify,
        fontWeight = FontWeight(400),
        color = Black,
    )

    fun notifSecond() = TextStyle(
        fontFamily = FontFamily(Font(R.font.inter_medium)),
        fontSize = 12.sp,
        fontWeight = FontWeight(400),
        color = Grey,
    )

    fun statusBold() = TextStyle(
        fontFamily = FontFamily(Font(R.font.inter_regular)),
        fontSize = 15.sp,
        fontWeight = FontWeight(700),
        color = Black,
    )

    fun statusRegular() = TextStyle(
        fontFamily = FontFamily(Font(R.font.inter_regular)),
        fontSize = 15.sp,
        fontWeight = FontWeight(400),
        color = Black,
    )

    fun profileName() = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_bold)),
        fontSize = 17.sp,
        fontWeight = FontWeight(600),
        color = Black,
    )

    fun profileStatus() = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_regular)),
        fontSize = 12.sp,
        fontWeight = FontWeight(400),
        color = Black,
    )

    fun profilOption() = TextStyle(
        fontFamily = FontFamily(Font(R.font.inter_regular)),
        fontSize = 12.sp,
        fontWeight = FontWeight(400),
        color = Black,
    )

    fun paymentTitle() = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_bold)),
        fontSize = 24.sp,
        fontWeight = FontWeight(600),
        color = White,
    )
    fun paymentChoose() = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_bold)),
        fontSize = 15.sp,
        fontWeight = FontWeight(700),
        color = Black,
    )
    fun paymentNoteStart() = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_medium)),
        fontSize = 14.sp,
        fontWeight = FontWeight(500),
        color = Black,
        textAlign = TextAlign.Start
    )

    fun paymentNoteCenter() = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_medium)),
        fontSize = 14.sp,
        fontWeight = FontWeight(500),
        color = Black,
        textAlign = TextAlign.Center
    )

    fun paymentNoteEnd() = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_medium)),
        fontSize = 14.sp,
        fontWeight = FontWeight(500),
        color = Black,
        textAlign = TextAlign.End
    )

    fun paymentTotal() = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_medium)),
        fontSize = 24.sp,
        fontWeight = FontWeight(700),
        color = Black,
        textAlign = TextAlign.End
    )

    fun confirmInfo() = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_medium)),
        fontSize = 16.sp,
        fontWeight = FontWeight(500),
        color = Black,
    )

    fun confirmInfoBold() = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_bold)),
        fontSize = 16.sp,
        fontWeight = FontWeight(600),
        color = Black,
    )
    fun receiptSuccess() = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_regular)),
        fontSize = 20.sp,
        fontWeight = FontWeight(600),
        color = Black,
    )
    fun receiptTime() = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_regular)),
        fontSize = 12.sp,
        fontWeight = FontWeight(500),
        color = Grey,
    )

    fun receiptTotal() = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_medium)),
        fontSize = 24.sp,
        fontWeight = FontWeight(700),
        color = Black,
        textAlign = TextAlign.Center
    )

    fun receiptShare() = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_medium)),
        fontSize = 14.sp,
        fontWeight = FontWeight(700),
        color = Color(0xFF2176AE),
    )

    fun receiptInfo() = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_medium)),
        fontSize = 14.sp,
        fontWeight = FontWeight(500),
        color = Color(0xFF2176AE),
    )

    fun statusTitle() = TextStyle(
        fontFamily = FontFamily(Font(R.font.inter_bold)),
        fontSize = 20.sp,
        fontWeight = FontWeight(700),
        color = Black
    )

    fun statusButton() = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_medium)),
        fontSize = 16.sp,
        fontWeight = FontWeight(600),
        color = White
    )



}
