package com.example.starwarsapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.starwarsapp.data.model.Person
import com.example.starwarsapp.ui.theme.*

// ── Detail Screen ─────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    person: Person,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = person.name,
                        color = YellowSW,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver",
                            tint = YellowSW
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SpaceGray)
            )
        },
        containerColor = DeepSpace
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .background(DeepSpace)
        ) {
            // Hero image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .background(SpaceGray),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = person.avatarUrl,
                    contentDescription = person.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(180.dp)
                        .clip(CircleShape)
                        .border(3.dp, YellowSW, CircleShape)
                )
            }

            Spacer(Modifier.height(24.dp))

            // Name header
            Text(
                text = person.name,
                style = MaterialTheme.typography.displayLarge,
                color = YellowSW,
                fontWeight = FontWeight.Black,
                modifier = Modifier.padding(horizontal = 20.dp)
            )

            Spacer(Modifier.height(20.dp))

            // Stats section
            SectionTitle("Datos físicos")
            StatCard(stats = listOf(
                "Altura"     to "${person.height} cm",
                "Masa"       to "${person.mass} kg",
                "Color de pelo" to person.hair_color.replaceFirstChar { it.uppercase() },
                "Color de piel" to person.skin_color.replaceFirstChar { it.uppercase() },
                "Color de ojos" to person.eye_color.replaceFirstChar { it.uppercase() }
            ))

            Spacer(Modifier.height(16.dp))

            SectionTitle("Perfil")
            StatCard(stats = listOf(
                "Año de nacimiento" to person.birth_year,
                "Género"            to person.genderLabel,
                "Películas"         to "${person.films.size}",
                "Naves estelares"   to "${person.starships.size}",
                "Vehículos"         to "${person.vehicles.size}"
            ))

            Spacer(Modifier.height(32.dp))
        }
    }
}

@Composable
private fun SectionTitle(text: String) {
    Text(
        text = text.uppercase(),
        style = MaterialTheme.typography.labelSmall,
        color = LightSaber,
        letterSpacing = 2.sp,
        modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)
    )
}

@Composable
private fun StatCard(stats: List<Pair<String, String>>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = SpaceGray)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            stats.forEachIndexed { index, (label, value) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MutedGray
                    )
                    Text(
                        text = value,
                        style = MaterialTheme.typography.bodyMedium,
                        color = StarWhite,
                        fontWeight = FontWeight.Medium
                    )
                }
                if (index < stats.lastIndex) {
                    HorizontalDivider(color = SpaceGrayLight, thickness = 0.5.dp)
                }
            }
        }
    }
}
