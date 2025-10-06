package com.example.todo.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

// Palette
val DarkRed = Color(0xFF662222)
val WineRed = Color(0xFF842A3B)
val SoftRed = Color(0xFFA3485A)
val LightCream = Color(0xFFF5DAA7)

// Dark theme
private val DarkColorScheme = darkColorScheme(
    primary = DarkRed,
    secondary = WineRed,
    tertiary = SoftRed,
    background = Color.Black,
    surface = DarkRed,
    onPrimary = LightCream,
    onSecondary = LightCream,
    onTertiary = LightCream,
    onBackground = LightCream,
    onSurface = LightCream
)

// Light theme
private val LightColorScheme = lightColorScheme(
    primary = SoftRed,
    secondary = WineRed,
    tertiary = DarkRed,
    background = LightCream,
    surface = LightCream,
    onPrimary = LightCream,
    onSecondary = LightCream,
    onTertiary = LightCream,
    onBackground = DarkRed,
    onSurface = DarkRed
)

@Composable
fun ToDoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}