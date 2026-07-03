package com.compassai.core_ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

/**
 * Root theme composable for Compass AI.
 *
 * Supports:
 * - Dynamic color (Material You) on Android 12+ — uses the user's wallpaper
 *   colors as the primary palette, which feels native and personal.
 * - Falls back to our brand palette (teal + amber) on older Android versions.
 * - Dark mode support via system setting.
 *
 * Applied once in MainActivity. All screens below it inherit the theme
 * automatically through MaterialTheme.colorScheme / typography / shapes.
 */
@Composable
fun CompassAiTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context)
            else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = CompassAiTypography,
        content = content,
    )
}
