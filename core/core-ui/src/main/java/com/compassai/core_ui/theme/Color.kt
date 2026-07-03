package com.compassai.core_ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// Brand palette — professional, trustworthy, growth-oriented
// Compass metaphor: deep teal (direction) + warm amber (potential)
internal val TealPrimary       = Color(0xFF00695C)
internal val TealPrimaryDark   = Color(0xFF004D40)
internal val TealContainer     = Color(0xFFB2DFDB)
internal val TealOnContainer   = Color(0xFF00352C)

internal val AmberSecondary    = Color(0xFFF57F17)
internal val AmberContainer    = Color(0xFFFFF3E0)
internal val AmberOnContainer  = Color(0xFF4E2600)

internal val ErrorRed          = Color(0xFFBA1A1A)
internal val ErrorContainer    = Color(0xFFFFDAD6)

internal val NeutralBackground = Color(0xFFF8FAF9)
internal val NeutralSurface    = Color(0xFFFFFFFF)
internal val NeutralOutline    = Color(0xFF79747E)

internal val LightColorScheme = lightColorScheme(
    primary              = TealPrimary,
    onPrimary            = Color.White,
    primaryContainer     = TealContainer,
    onPrimaryContainer   = TealOnContainer,
    secondary            = AmberSecondary,
    onSecondary          = Color.White,
    secondaryContainer   = AmberContainer,
    onSecondaryContainer = AmberOnContainer,
    error                = ErrorRed,
    errorContainer       = ErrorContainer,
    background           = NeutralBackground,
    onBackground         = Color(0xFF191C1B),
    surface              = NeutralSurface,
    onSurface            = Color(0xFF191C1B),
    surfaceVariant       = Color(0xFFDBE5E0),
    onSurfaceVariant     = Color(0xFF3F4945),
    outline              = NeutralOutline,
)

internal val DarkColorScheme = darkColorScheme(
    primary              = Color(0xFF80CBC4),
    onPrimary            = Color(0xFF00352C),
    primaryContainer     = TealPrimaryDark,
    onPrimaryContainer   = TealContainer,
    secondary            = Color(0xFFFFCC80),
    onSecondary          = Color(0xFF4E2600),
    secondaryContainer   = Color(0xFF6D3900),
    onSecondaryContainer = Color(0xFFFFDDB3),
    error                = Color(0xFFFFB4AB),
    errorContainer       = Color(0xFF93000A),
    background           = Color(0xFF191C1B),
    onBackground         = Color(0xFFE0E3E1),
    surface              = Color(0xFF191C1B),
    onSurface            = Color(0xFFE0E3E1),
    surfaceVariant       = Color(0xFF3F4945),
    onSurfaceVariant     = Color(0xFFBEC9C3),
    outline              = Color(0xFF88938E),
)
