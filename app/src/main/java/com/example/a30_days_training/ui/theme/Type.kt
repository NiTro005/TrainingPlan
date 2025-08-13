package com.example.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.a30_days_training.R

val RobotoFlex = FontFamily(
    Font(R.font.roboto_flex)
)

val Ubuntu = FontFamily(
    Font(R.font.ubuntu_regular),
    Font(R.font.ubuntu_bold, weight = FontWeight.Bold)
)


val AppTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = Ubuntu,
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp
    ),
    displayMedium = TextStyle(
        fontFamily = Ubuntu,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp
    ),
    labelSmall = TextStyle(
        fontFamily = Ubuntu,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold
    ),
    bodyLarge = TextStyle(
        fontFamily = RobotoFlex,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    )


)

