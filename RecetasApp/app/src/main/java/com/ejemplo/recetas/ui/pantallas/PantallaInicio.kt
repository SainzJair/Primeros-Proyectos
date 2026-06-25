package com.ejemplo.recetas.ui.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ejemplo.recetas.ui.componentes.BarraBusqueda
import com.ejemplo.recetas.ui.componentes.ChipCategoria
import com.ejemplo.recetas.ui.componentes.TarjetaReceta
import com.ejemplo.recetas.ui.tema.*
import com.ejemplo.recetas.viewmodel.RecetaViewModel

/**
 * Pantalla principal: Lista de Recetas.
 *
 * Unidades cubiertas:
 *  · U1 — Composable functions, Text, alineación
 *  · U2 — State, remember, mutableStateOf, interacción con botones
 *  · U3 — LazyColumn, Material Theme, Data Classes
 *  · U4 — ViewModel, StateFlow, navegación
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaInicio(
    viewModel: RecetaViewModel,
    alSeleccionarReceta: (Int) -> Unit
) {
    // ── Unidad 4 · StateFlow convertido a State de Compose ────────────────
    val recetasFiltradas by viewModel.recetasFiltradas.collectAsStateWithLifecycle()
    val textoBusqueda    by viewModel.textoBusqueda.collectAsStateWithLifecycle()
    val categoriaActiva  by viewModel.categoriaActiva.collectAsStateWithLifecycle()

    // ── Unidad 2 · Estado local para mostrar/ocultar búsqueda ─────────────
    var mostrarBusqueda  by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text       = "🍽️ RecetasApp",
                            style      = MaterialTheme.typography.titleLarge,
                            color      = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text  = "${recetasFiltradas.size} recetas disponibles",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.White.copy(alpha = 0.8f)
                        )
                    }
                },
                actions = {
                    // Unidad 2 · Botón con interacción — alterna búsqueda
                    IconButton(onClick = { mostrarBusqueda = !mostrarBusqueda }) {
                        Text(
                            text  = if (mostrarBusqueda) "✕" else "🔍",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = ColorPrimario
                )
            )
        }
    ) { paddingInterior ->

        // ── Unidad 3 · LazyColumn ─────────────────────────────────────────
        LazyColumn(
            modifier            = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingInterior),
            contentPadding      = PaddingValues(bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {

            // ── ENCABEZADO HERO ───────────────────────────────────────────
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    ColorPrimario.copy(alpha = 0.9f),
                                    ColorPrimarioClaro.copy(alpha = 0.7f),
                                    MaterialTheme.colorScheme.background
                                )
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text  = "¡Bienvenido a la cocina!",
                            style = MaterialTheme.typography.headlineMedium,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text  = "Descubre sabores auténticos de México",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White.copy(alpha = 0.9f)
                        )
                    }
                }
            }

            // ── BARRA DE BÚSQUEDA (condicional) ───────────────────────────
            // Unidad 2 · Condicional en Kotlin dentro de Compose
            if (mostrarBusqueda) {
                item {
                    BarraBusqueda(
                        texto           = textoBusqueda,
                        alCambiarTexto  = viewModel::actualizarBusqueda
                    )
                }
            }

            // ── CHIPS DE CATEGORÍA ────────────────────────────────────────
            item {
                LazyRow(
                    modifier            = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(viewModel.categorias) { categoria ->
                        ChipCategoria(
                            etiqueta    = categoria,
                            estaActivo  = categoriaActiva == categoria,
                            alHacerClic = { viewModel.cambiarCategoria(categoria) }
                        )
                    }
                }
            }

            // ── TÍTULO DE SECCIÓN ─────────────────────────────────────────
            item {
                Text(
                    text     = "Recetas",
                    style    = MaterialTheme.typography.headlineMedium,
                    color    = ColorTextoOscuro,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }

            // ── LISTA DE TARJETAS ─────────────────────────────────────────
            // Unidad 3 · LazyColumn con items de Data Class
            if (recetasFiltradas.isEmpty()) {
                item {
                    Box(
                        modifier           = Modifier
                            .fillMaxWidth()
                            .padding(48.dp),
                        contentAlignment   = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "😔", style = MaterialTheme.typography.displayLarge)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text  = "No se encontraron recetas",
                                style = MaterialTheme.typography.titleMedium,
                                color = ColorTextoMedio
                            )
                        }
                    }
                }
            } else {
                items(
                    items = recetasFiltradas,
                    key   = { receta -> receta.id }
                ) { receta ->
                    TarjetaReceta(
                        receta       = receta,
                        alHacerClic  = { alSeleccionarReceta(receta.id) }
                    )
                }
            }

            // ── PIE DE PÁGINA ─────────────────────────────────────────────
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text     = "🌶️ Hecho con amor en México",
                    style    = MaterialTheme.typography.labelSmall,
                    color    = ColorTextoClarito,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
            }
        }
    }
}
