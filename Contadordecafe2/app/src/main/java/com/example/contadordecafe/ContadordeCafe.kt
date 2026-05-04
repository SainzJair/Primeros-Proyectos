package com.example.contadordecafe

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Composable con estado (Stateful) — aquí vive el estado
@Composable
fun ContadordeCafe() {
    var contadorTazas by remember { mutableStateOf(0) }

    ContenidoContadorCafe(
        conteo        = contadorTazas,
        alAgregarTaza = { contadorTazas++ },
        alReiniciar   = { contadorTazas = 0 }
    )
}

// Composable sin estado (Stateless) — solo recibe datos y emite eventos
@Composable
fun ContenidoContadorCafe(
    conteo: Int,
    alAgregarTaza: () -> Unit,
    alReiniciar:   () -> Unit
) {
    val superaElLimite = conteo >= 10

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement  = Arrangement.Center
    ) {

        // Título
        Text(text = "☕ Contador de Café", fontSize = 22.sp, fontWeight = FontWeight.Medium)

        Spacer(modifier = Modifier.height(32.dp))

        // Tarjeta del contador
        Card(
            shape  = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier            = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text  = "tazas hoy",
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text       = conteo.toString(),
                    fontSize   = 72.sp,
                    fontWeight = FontWeight.Medium,
                    // 🔴 Color rojo si supera el límite
                    color = if (superaElLimite) MaterialTheme.colorScheme.error
                    else MaterialTheme.colorScheme.onSurface
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Mensaje de advertencia — aparece cuando conteo >= 10
        if (superaElLimite) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer
                ),
                shape    = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text       = "⚠ ¡Demasiada cafeína!",
                    color      = MaterialTheme.colorScheme.onErrorContainer,
                    fontWeight = FontWeight.Medium,
                    modifier   = Modifier.padding(12.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Botón principal
        Button(
            onClick  = alAgregarTaza,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "+ Añadir taza", fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Botón de reinicio
        TextButton(onClick = alReiniciar) {
            Text(text = "Reiniciar", color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}