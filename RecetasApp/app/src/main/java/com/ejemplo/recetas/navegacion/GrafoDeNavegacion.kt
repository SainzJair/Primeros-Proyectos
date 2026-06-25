package com.ejemplo.recetas.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ejemplo.recetas.ui.pantallas.PantallaDetalle
import com.ejemplo.recetas.ui.pantallas.PantallaInicio
import com.ejemplo.recetas.viewmodel.RecetaViewModel

/**
 * Configuración del grafo de navegación.
 * Unidad 4 — NavHost con dos rutas: Inicio y Detalle
 */
@Composable
fun GrafoDeNavegacion(viewModel: RecetaViewModel) {

    val controladorNav = rememberNavController()

    NavHost(
        navController = controladorNav,
        startDestination = Pantalla.Inicio.ruta
    ) {

        // ── Ruta: Lista de Recetas ─────────────────────────────────────────
        composable(route = Pantalla.Inicio.ruta) {
            PantallaInicio(
                viewModel = viewModel,
                alSeleccionarReceta = { idReceta ->
                    viewModel.seleccionarReceta(idReceta)
                    controladorNav.navigate(Pantalla.Detalle.conArgumento(idReceta))
                }
            )
        }

        // ── Ruta: Detalle de Receta ────────────────────────────────────────
        composable(
            route = Pantalla.Detalle.ruta,
            arguments = listOf(
                navArgument(Pantalla.Detalle.ARGUMENTO_ID) {
                    type = NavType.IntType
                }
            )
        ) { entradaAtras ->
            val idReceta = entradaAtras.arguments?.getInt(Pantalla.Detalle.ARGUMENTO_ID) ?: 0
            PantallaDetalle(
                idReceta = idReceta,
                viewModel = viewModel,
                alRegresar = {
                    viewModel.limpiarSeleccion()
                    controladorNav.popBackStack()
                }
            )
        }
    }
}
