package com.ejemplo.recetas.viewmodel

import androidx.lifecycle.ViewModel
import com.ejemplo.recetas.modelo.Receta
import com.ejemplo.recetas.modelo.RepositorioRecetas
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel principal de la aplicación.
 * Unidad 4 — ViewModel + StateFlow
 *
 * Gestiona:
 *  - La lista completa de recetas
 *  - La receta actualmente seleccionada
 *  - El texto de búsqueda para filtrar recetas
 */
class RecetaViewModel : ViewModel() {

    // ── Lista completa (inmutable) ─────────────────────────────────────────
    private val _listaDeRecetas = MutableStateFlow(RepositorioRecetas.listaDeRecetas)
    val listaDeRecetas: StateFlow<List<Receta>> = _listaDeRecetas.asStateFlow()

    // ── Receta seleccionada ────────────────────────────────────────────────
    private val _recetaSeleccionada = MutableStateFlow<Receta?>(null)
    val recetaSeleccionada: StateFlow<Receta?> = _recetaSeleccionada.asStateFlow()

    // ── Texto de búsqueda ─────────────────────────────────────────────────
    private val _textoBusqueda = MutableStateFlow("")
    val textoBusqueda: StateFlow<String> = _textoBusqueda.asStateFlow()

    // ── Recetas filtradas ─────────────────────────────────────────────────
    private val _recetasFiltradas = MutableStateFlow(RepositorioRecetas.listaDeRecetas)
    val recetasFiltradas: StateFlow<List<Receta>> = _recetasFiltradas.asStateFlow()

    // ── Categoría activa para el filtro ───────────────────────────────────
    private val _categoriaActiva = MutableStateFlow("Todas")
    val categoriaActiva: StateFlow<String> = _categoriaActiva.asStateFlow()

    /** Categorías disponibles (extraídas de las recetas + "Todas") */
    val categorias: List<String> = listOf("Todas") +
            RepositorioRecetas.listaDeRecetas.map { it.categoria }.distinct()

    // ── Acciones ──────────────────────────────────────────────────────────

    /** Selecciona una receta por su ID para mostrar el detalle. */
    fun seleccionarReceta(idReceta: Int) {
        _recetaSeleccionada.value =
            RepositorioRecetas.listaDeRecetas.find { it.id == idReceta }
    }

    /** Limpia la receta seleccionada al regresar. */
    fun limpiarSeleccion() {
        _recetaSeleccionada.value = null
    }

    /** Actualiza el texto de búsqueda y vuelve a filtrar. */
    fun actualizarBusqueda(texto: String) {
        _textoBusqueda.value = texto
        aplicarFiltros()
    }

    /** Cambia la categoría activa y vuelve a filtrar. */
    fun cambiarCategoria(categoria: String) {
        _categoriaActiva.value = categoria
        aplicarFiltros()
    }

    /** Aplica búsqueda y categoría de forma combinada. */
    private fun aplicarFiltros() {
        val texto = _textoBusqueda.value.trim().lowercase()
        val categoria = _categoriaActiva.value

        _recetasFiltradas.value = RepositorioRecetas.listaDeRecetas.filter { receta ->
            val coincideTexto = texto.isEmpty() ||
                    receta.nombre.lowercase().contains(texto) ||
                    receta.descripcionCorta.lowercase().contains(texto)

            val coincideCategoria = categoria == "Todas" || receta.categoria == categoria

            coincideTexto && coincideCategoria
        }
    }
}
