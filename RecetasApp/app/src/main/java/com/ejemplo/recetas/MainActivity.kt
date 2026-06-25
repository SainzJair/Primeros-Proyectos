package com.ejemplo.recetas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.ejemplo.recetas.navegacion.GrafoDeNavegacion
import com.ejemplo.recetas.ui.tema.TemaRecetas
import com.ejemplo.recetas.viewmodel.RecetaViewModel

/**
 * Actividad principal de RecetasApp.
 *
 * Punto de entrada de la aplicación. Configura el tema visual y el
 * grafo de navegación con su ViewModel compartido.
 *
 * Unidad 1 — Primera app en Android: ComponentActivity + setContent
 * Unidad 4 — ViewModel instanciado con viewModels()
 */
class MainActivity : ComponentActivity() {

    // Unidad 4 · ViewModel compartido entre pantallas
    private val viewModel: RecetaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Permite que el contenido se extienda bajo la barra de estado
        enableEdgeToEdge()

        setContent {
            // Unidad 3 · Material Theme envolviendo toda la app
            TemaRecetas {
                // Unidad 4 · NavHost con sus dos rutas
                GrafoDeNavegacion(viewModel = viewModel)
            }
        }
    }
}
