package com.ejemplo.recetas.ui.componentes

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ejemplo.recetas.modelo.Receta
import com.ejemplo.recetas.ui.tema.*

// ─────────────────────────────────────────────────────────────────────────────
// TARJETA DE RECETA — Para LazyColumn en PantallaInicio
// Unidad 3 · Data Classes + Material Design + LazyColumn
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Tarjeta visual que muestra el resumen de una receta.
 * Al hacer clic navega al detalle.
 */
@Composable
fun TarjetaReceta(
    receta: Receta,
    alHacerClic: () -> Unit,
    modifier: Modifier = Modifier
) {
    var presionado by remember { mutableStateOf(false) }  // Unidad 2 · remember + mutableStateOf

    val colorFondo by animateColorAsState(
        targetValue = if (presionado)
            Color(receta.colorFondo).copy(alpha = 0.15f)
        else
            MaterialTheme.colorScheme.surface,
        animationSpec = tween(200),
        label = "colorFondoTarjeta"
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .shadow(elevation = 4.dp, shape = MaterialTheme.shapes.medium)
            .clickable {
                presionado = true
                alHacerClic()
            },
        shape  = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = colorFondo)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Emoji / Ícono de receta
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        Brush.radialGradient(
                            colors = listOf(
                                Color(receta.colorFondo).copy(alpha = 0.3f),
                                Color(receta.colorFondo).copy(alpha = 0.1f)
                            )
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text     = receta.emoji,
                    fontSize = 36.sp
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Información de la receta
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text       = receta.nombre,
                    style      = MaterialTheme.typography.titleMedium,
                    color      = ColorTextoOscuro,
                    maxLines   = 1,
                    overflow   = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text     = receta.descripcionCorta,
                    style    = MaterialTheme.typography.bodyMedium,
                    color    = ColorTextoMedio,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Chips de metadatos
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    ChipMiniatura(
                        etiqueta = "⏱ ${receta.tiempoPreparacion}",
                        color    = ColorPrimario
                    )
                    ChipMiniatura(
                        etiqueta = receta.dificultad,
                        color    = when (receta.dificultad) {
                            "Fácil"  -> Color(0xFF2E7D32)
                            "Media"  -> Color(0xFFF57F17)
                            else     -> Color(0xFFC62828)
                        }
                    )
                }
            }

            // Flecha indicadora
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text  = "›",
                style = MaterialTheme.typography.headlineMedium,
                color = Color(receta.colorFondo)
            )
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// CHIP MINIATURA
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun ChipMiniatura(
    etiqueta: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(color.copy(alpha = 0.12f))
            .padding(horizontal = 8.dp, vertical = 3.dp)
    ) {
        Text(
            text  = etiqueta,
            style = MaterialTheme.typography.labelSmall,
            color = color,
            fontWeight = FontWeight.SemiBold
        )
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// BARRA DE BÚSQUEDA
// Unidad 2 · State + mutableStateOf + interacción con campos de texto
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun BarraBusqueda(
    texto: String,
    alCambiarTexto: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value            = texto,
        onValueChange    = alCambiarTexto,
        modifier         = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        placeholder      = { Text("Buscar receta…") },
        leadingIcon      = {
            Icon(
                imageVector  = Icons.Default.Search,
                contentDescription = "Buscar",
                tint         = ColorPrimario
            )
        },
        singleLine       = true,
        shape            = MaterialTheme.shapes.large,
        colors           = OutlinedTextFieldDefaults.colors(
            focusedBorderColor   = ColorPrimario,
            unfocusedBorderColor = Color(0xFFD7CCC8)
        )
    )
}

// ─────────────────────────────────────────────────────────────────────────────
// CHIPS DE CATEGORÍA
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun ChipCategoria(
    etiqueta: String,
    estaActivo: Boolean,
    alHacerClic: () -> Unit,
    modifier: Modifier = Modifier
) {
    FilterChip(
        selected  = estaActivo,
        onClick   = alHacerClic,
        label     = { Text(etiqueta, style = MaterialTheme.typography.labelSmall) },
        modifier  = modifier,
        colors    = FilterChipDefaults.filterChipColors(
            selectedContainerColor    = ColorPrimario,
            selectedLabelColor        = Color.White,
            containerColor            = MaterialTheme.colorScheme.surfaceVariant,
            labelColor                = ColorTextoMedio
        )
    )
}

// ─────────────────────────────────────────────────────────────────────────────
// FILA DE INGREDIENTE — Para pantalla Detalle
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun FilaIngrediente(
    numero: Int,
    ingrediente: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier          = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier        = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .background(ColorPrimario.copy(alpha = 0.12f)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text  = "$numero",
                style = MaterialTheme.typography.labelSmall,
                color = ColorPrimario,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text  = ingrediente,
            style = MaterialTheme.typography.bodyMedium,
            color = ColorTextoMedio
        )
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// FILA DE PASO — Para pantalla Detalle
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun FilaPaso(
    numero: Int,
    paso: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier          = modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier        = Modifier
                .size(28.dp)
                .clip(CircleShape)
                .background(ColorPrimario),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text  = "$numero",
                style = MaterialTheme.typography.labelSmall,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text  = paso,
            style = MaterialTheme.typography.bodyLarge,
            color = ColorTextoOscuro
        )
    }
}
