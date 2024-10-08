package com.esmailelhanash.remotekeyboard.ui.theme

import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = OldRose,
    onPrimary = BlackBean,
    primaryContainer = DarkChampagne,
    secondary = BattleshipGray,
    onSecondary = Color(0xFFEEEEEE),
    secondaryContainer = DarkSlateGray,
    tertiary = Khaki,
    onTertiary = BlackBean,
    background = BlackBean,
    onBackground = Champagne,
    surface = LightGraySurface,
    onSurface = Champagne,
    error = Color(0xFFB00020),
    onError = Color(0xFFFFFFFF)
)



private val LightColorScheme = lightColorScheme(
    primary = OldRose,
    onPrimary = DarkRedText,
    primaryContainer = PinkBlush,
    secondary = BattleshipGray,
    onSecondary = GrayText,
    secondaryContainer = LightSlateGray,
    tertiary = Khaki,
    onTertiary = DarkTextOnBackground,
    background = Champagne,
    onBackground = BlackBean,
    surface = Cream,
    onSurface = BlackBean,
    error = Color(0xFFB00020),
    onError = Color(0xFFFFFFFF)
)


@Composable
fun RemoteKeyboardTheme(
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
             dynamicLightColorScheme(context)
        }
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}