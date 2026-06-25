package com.ejemplo.recetas.ui.tema

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape

// ─────────────────────────────────────────────────────────────────────────────
// COLORES — Unidad 3 · Material Theme (colores)
// Paleta inspirada en la cocina mexicana: terracota, verde lima, crema
// ─────────────────────────────────────────────────────────────────────────────

val ColorPrimario        = Color(0xFFBF360C)   // Terracota oscuro
val ColorPrimarioClaro   = Color(0xFFE64A19)   // Terracota medio
val ColorSecundario      = Color(0xFF2E7D32)   // Verde mexicano
val ColorTerciario       = Color(0xFFF57F17)   // Amarillo maíz
val ColorFondoClaro      = Color(0xFFFFF8F5)   // Crema cálida
val ColorSuperficie      = Color(0xFFFFECE3)   // Superficie rosada
val ColorTextoOscuro     = Color(0xFF1A0A00)   // Casi negro cálido
val ColorTextoMedio      = Color(0xFF5D3A1A)   // Café medio
val ColorTextoClarito    = Color(0xFF8D6E63)   // Café claro

private val EsquemaColoresClaro = lightColorScheme(
    primary          = ColorPrimario,
    onPrimary        = Color.White,
    primaryContainer = Color(0xFFFFDBCC),
    secondary        = ColorSecundario,
    onSecondary      = Color.White,
    tertiary         = ColorTerciario,
    background       = ColorFondoClaro,
    surface          = ColorSuperficie,
    onBackground     = ColorTextoOscuro,
    onSurface        = ColorTextoOscuro,
    surfaceVariant   = Color(0xFFFFF3EE),
    outline          = Color(0xFFD7CCC8)
)

// ─────────────────────────────────────────────────────────────────────────────
// TIPOGRAFÍA — Unidad 3 · Material Theme (tipografía)
// ─────────────────────────────────────────────────────────────────────────────

val TipografiaRecetas = Typography(
    displayLarge = TextStyle(
        fontFamily   = FontFamily.Serif,
        fontWeight   = FontWeight.Bold,
        fontSize     = 32.sp,
        lineHeight   = 40.sp,
        color        = ColorTextoOscuro
    ),
    headlineLarge = TextStyle(
        fontFamily   = FontFamily.Serif,
        fontWeight   = FontWeight.SemiBold,
        fontSize     = 26.sp,
        lineHeight   = 34.sp
    ),
    headlineMedium = TextStyle(
        fontFamily   = FontFamily.Serif,
        fontWeight   = FontWeight.SemiBold,
        fontSize     = 22.sp,
        lineHeight   = 30.sp
    ),
    titleLarge = TextStyle(
        fontFamily   = FontFamily.Default,
        fontWeight   = FontWeight.Bold,
        fontSize     = 20.sp,
        lineHeight   = 28.sp
    ),
    titleMedium = TextStyle(
        fontFamily   = FontFamily.Default,
        fontWeight   = FontWeight.SemiBold,
        fontSize     = 16.sp,
        lineHeight   = 24.sp
    ),
    bodyLarge = TextStyle(
        fontFamily   = FontFamily.Default,
        fontWeight   = FontWeight.Normal,
        fontSize     = 16.sp,
        lineHeight   = 24.sp
    ),
    bodyMedium = TextStyle(
        fontFamily   = FontFamily.Default,
        fontWeight   = FontWeight.Normal,
        fontSize     = 14.sp,
        lineHeight   = 20.sp
    ),
    labelSmall = TextStyle(
        fontFamily   = FontFamily.Default,
        fontWeight   = FontWeight.Medium,
        fontSize     = 11.sp,
        lineHeight   = 16.sp,
        letterSpacing = 0.5.sp
    )
)

// ─────────────────────────────────────────────────────────────────────────────
// FORMAS — Unidad 3 · Material Theme (formas)
// ─────────────────────────────────────────────────────────────────────────────

val FormasRecetas = Shapes(
    extraSmall = RoundedCornerShape(4.dp),
    small      = RoundedCornerShape(8.dp),
    medium     = RoundedCornerShape(16.dp),
    large      = RoundedCornerShape(24.dp),
    extraLarge = RoundedCornerShape(32.dp)
)

// ─────────────────────────────────────────────────────────────────────────────
// TEMA PRINCIPAL
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Tema visual principal de RecetasApp.
 * Unidad 3 — Material Theme completo: colores + tipografía + formas.
 */
@Composable
fun TemaRecetas(
    contenido: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = EsquemaColoresClaro,
        typography  = TipografiaRecetas,
        shapes      = FormasRecetas,
        content     = contenido
    )
}
