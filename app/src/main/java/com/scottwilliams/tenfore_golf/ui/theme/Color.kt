package com.scottwilliams.tenfore_golf.ui.theme


import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// Your brand colors (customize if you want)
val PrimaryBlue = Color(0xFF1565C0)
val SecondaryBlue = Color(0xFF42A5F5)
val White = Color(0xFFFFFFFF)
val Black = Color(0xFF000000)

val LightColorScheme = lightColorScheme(
    primary = PrimaryBlue,
    secondary = SecondaryBlue,
    background = White,
    surface = White,
    onPrimary = White,
    onSecondary = Black,
)
