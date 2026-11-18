
package br.com.fiap.vigiabairro.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val VigiaBairroColors = lightColorScheme(
    primary = PrimaryBlue,
    secondary = SecondaryBlue,
    tertiary = AccentCyan,

    background = BackgroundLight,
    surface = White,

    onPrimary = White,
    onSecondary = White,
    onBackground = TextDark,
    onSurface = TextDark
)

@Composable
fun VigiaBairroTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = VigiaBairroColors,
        content = content
    )
}
