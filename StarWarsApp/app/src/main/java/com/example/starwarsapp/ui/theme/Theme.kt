package com.example.starwarsapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary            = YellowSW,
    onPrimary          = DeepSpace,
    primaryContainer   = SpaceGrayLight,
    onPrimaryContainer = YellowSWLight,
    secondary          = LightSaber,
    onSecondary        = DeepSpace,
    background         = DeepSpace,
    onBackground       = StarWhite,
    surface            = SpaceGray,
    onSurface          = StarWhite,
    surfaceVariant     = SpaceGrayLight,
    onSurfaceVariant   = MutedGray,
    error              = DarkSaber,
    onError            = StarWhite
)

@Composable
fun StarWarsAppTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography  = Typography,
        content     = content
    )
}
