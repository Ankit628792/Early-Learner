package com.earlylearner.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = CoralRed,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFFFE5E5),
    onPrimaryContainer = CoralRedDark,
    secondary = SkyBlue,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFE0F7FA),
    onSecondaryContainer = OceanBlue,
    tertiary = MintGreen,
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFE8F8F5),
    onTertiaryContainer = ForestGreen,
    background = CreamBackground,
    onBackground = CharcoalText,
    surface = CardBackgroundWhite,
    onSurface = CharcoalText,
    surfaceVariant = SoftPeachSurface,
    onSurfaceVariant = SlateSubtitle,
    outline = WarmBorder
)

private val DarkColorScheme = lightColorScheme(
    primary = CoralRed,
    onPrimary = Color.White,
    primaryContainer = Color(0xFF4A1E1E),
    onPrimaryContainer = Color(0xFFFFD5D5),
    secondary = SkyBlue,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFF133644),
    onSecondaryContainer = Color(0xFFBBEBFF),
    tertiary = MintGreen,
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFF103E33),
    onTertiaryContainer = Color(0xFFB4F8E5),
    background = Color(0xFF1F2028),
    onBackground = Color(0xFFF0F0F5),
    surface = Color(0xFF292B38),
    onSurface = Color(0xFFF0F0F5),
    surfaceVariant = Color(0xFF333647),
    onSurfaceVariant = Color(0xFFD0D2DE),
    outline = Color(0xFF484C62)
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
