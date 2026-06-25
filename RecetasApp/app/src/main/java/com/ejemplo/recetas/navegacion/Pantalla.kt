package com.ejemplo.recetas.navegacion

/**
 * Define las rutas de navegación de la aplicación.
 * Unidad 4 — NavHost + Navigation Controller
 */
sealed class Pantalla(val ruta: String) {

    /** Pantalla principal con la lista de recetas. */
    object Inicio : Pantalla("inicio")

    /** Pantalla de detalle; recibe el ID de la receta como argumento. */
    object Detalle : Pantalla("detalle/{idReceta}") {
        /** Construye la ruta con el ID concreto para navegar. */
        fun conArgumento(idReceta: Int): String = "detalle/$idReceta"

        /** Nombre del argumento de navegación. */
        const val ARGUMENTO_ID = "idReceta"
    }
}
