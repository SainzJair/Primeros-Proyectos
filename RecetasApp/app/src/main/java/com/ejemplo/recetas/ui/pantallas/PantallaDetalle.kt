package com.ejemplo.recetas.ui.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ejemplo.recetas.ui.componentes.FilaIngrediente
import com.ejemplo.recetas.ui.componentes.FilaPaso
import com.ejemplo.recetas.ui.tema.*
import com.ejemplo.recetas.viewmodel.RecetaViewModel

/**
 * Pantalla de Detalle de Receta.
 *
 * Unidades cubiertas:
 *  · U1 — Composable functions, Text, Image, alineación
 *  · U2 — State, remember, mutableStateOf (pestañas activas)
 *  · U3 — LazyColumn, Material Theme, Data Classes
 *  · U4 — ViewModel, StateFlow, argumento de navegación (idReceta)
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaDetalle(
    idReceta: Int,
    viewModel: RecetaViewModel,
    alRegresar: () -> Unit
) {
    // ── Unidad 4 · StateFlow → State ──────────────────────────────────────
    val recetaSeleccionada by viewModel.recetaSeleccionada.collectAsStateWithLifecycle()

    // Si por alguna razón la receta no está en el ViewModel, buscarla por ID
    LaunchedEffect(idReceta) {
        if (recetaSeleccionada == null || recetaSeleccionada?.id != idReceta) {
            viewModel.seleccionarReceta(idReceta)
        }
    }

    // ── Unidad 2 · Estado local para la pestaña activa ───────────────────
    var pestañaActiva by remember { mutableStateOf(0) }  // 0=Ingredientes, 1=Pasos

    val receta = recetaSeleccionada

    // ── Pantalla de carga si la receta aún no está disponible ─────────────
    if (receta == null) {
        Box(
            modifier         = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = ColorPrimario)
        }
        return
    }

    val colorReceta = Color(receta.colorFondo)

    Scaffold(
        topBar = {
            TopAppBar(
                title  = {
                    Text(
                        text   = receta.nombre,
                        style  = MaterialTheme.typography.titleLarge,
                        color  = Color.White,
                        maxLines = 1
                    )
                },
                navigationIcon = {
                    // Unidad 2 · Botón con interacción ─ regresar
                    IconButton(onClick = alRegresar) {
                        Icon(
                            imageVector        = Icons.Default.ArrowBack,
                            contentDescription = "Regresar",
                            tint               = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorReceta
                )
            )
        }
    ) { paddingInterior ->

        LazyColumn(
            modifier       = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingInterior),
            contentPadding = PaddingValues(bottom = 32.dp)
        ) {

            // ── HERO DE LA RECETA ─────────────────────────────────────────
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    colorReceta.copy(alpha = 0.85f),
                                    colorReceta.copy(alpha = 0.4f),
                                    MaterialTheme.colorScheme.background
                                )
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        // Unidad 1 · Text + alineación
                        Text(text = receta.emoji, fontSize = 80.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text       = receta.nombre,
                            style      = MaterialTheme.typography.headlineLarge,
                            color      = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text  = receta.categoria,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White.copy(alpha = 0.85f)
                        )
                    }
                }
            }

            // ── MÉTRICAS RÁPIDAS ──────────────────────────────────────────
            item {
                Row(
                    modifier              = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    MetricaReceta(icono = "⏱", valor = receta.tiempoPreparacion, etiqueta = "Tiempo")
                    Divider(
                        modifier  = Modifier
                            .height(48.dp)
                            .width(1.dp),
                        color     = MaterialTheme.colorScheme.outline
                    )
                    MetricaReceta(icono = "👥", valor = "${receta.porciones}", etiqueta = "Porciones")
                    Divider(
                        modifier = Modifier
                            .height(48.dp)
                            .width(1.dp),
                        color    = MaterialTheme.colorScheme.outline
                    )
                    MetricaReceta(icono = "📊", valor = receta.dificultad, etiqueta = "Dificultad")
                }
            }

            // ── DESCRIPCIÓN ────────────────────────────────────────────────
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp),
                    shape  = MaterialTheme.shapes.medium,
                    colors = CardDefaults.cardColors(
                        containerColor = colorReceta.copy(alpha = 0.08f)
                    )
                ) {
                    Text(
                        text     = receta.descripcionLarga,
                        style    = MaterialTheme.typography.bodyLarge,
                        color    = ColorTextoOscuro,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }

            // ── PESTAÑAS: Ingredientes / Pasos ────────────────────────────
            // Unidad 2 · State + condicionales para cambiar vista
            item {
                Spacer(modifier = Modifier.height(16.dp))
                TabRow(
                    selectedTabIndex = pestañaActiva,
                    modifier         = Modifier.padding(horizontal = 16.dp),
                    containerColor   = MaterialTheme.colorScheme.surfaceVariant,
                    contentColor     = colorReceta
                ) {
                    Tab(
                        selected = pestañaActiva == 0,
                        onClick  = { pestañaActiva = 0 },
                        text     = {
                            Text(
                                "🧂 Ingredientes",
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    )
                    Tab(
                        selected = pestañaActiva == 1,
                        onClick  = { pestañaActiva = 1 },
                        text     = {
                            Text(
                                "👨‍🍳 Preparación",
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
            }

            // ── CONTENIDO SEGÚN PESTAÑA ───────────────────────────────────
            // Unidad 2 · Condicional en Kotlin dentro de Composable
            if (pestañaActiva == 0) {
                // Pestaña de Ingredientes
                itemsIndexed(receta.ingredientes) { indice, ingrediente ->
                    FilaIngrediente(
                        numero       = indice + 1,
                        ingrediente  = ingrediente,
                        modifier     = Modifier.padding(horizontal = 16.dp)
                    )
                }
            } else {
                // Pestaña de Pasos de preparación
                itemsIndexed(receta.pasos) { indice, paso ->
                    FilaPaso(
                        numero   = indice + 1,
                        paso     = paso,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    if (indice < receta.pasos.lastIndex) {
                        Divider(
                            modifier = Modifier.padding(horizontal = 28.dp, vertical = 6.dp),
                            color    = MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)
                        )
                    }
                }
            }

            // ── BOTÓN REGRESAR ────────────────────────────────────────────
            item {
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick  = alRegresar,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .height(52.dp),
                    shape    = MaterialTheme.shapes.large,
                    colors   = ButtonDefaults.buttonColors(
                        containerColor = colorReceta
                    )
                ) {
                    Text(
                        text  = "← Regresar a la lista",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                }
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Componente auxiliar: Métrica de receta (tiempo / porciones / dificultad)
// ─────────────────────────────────────────────────────────────────────────────

@Composable
private fun MetricaReceta(
    icono: String,
    valor: String,
    etiqueta: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier            = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = icono, fontSize = 22.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text       = valor,
            style      = MaterialTheme.typography.titleMedium,
            color      = ColorTextoOscuro,
            fontWeight = FontWeight.Bold
        )
        Text(
            text  = etiqueta,
            style = MaterialTheme.typography.labelSmall,
            color = ColorTextoClarito
        )
    }
}
