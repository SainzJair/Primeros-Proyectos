package com.example.starwarsapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.starwarsapp.data.model.Person
import com.example.starwarsapp.ui.components.*
import com.example.starwarsapp.ui.theme.*
import com.example.starwarsapp.viewmodel.PeopleUiState

// ── Home Screen ───────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    uiState: PeopleUiState,
    searchQuery: String,
    onSearchChange: (String) -> Unit,
    onPersonClick: (Person) -> Unit,
    onRetry: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "⭐ Star Wars",
                        style = MaterialTheme.typography.displayLarge,
                        color = YellowSW,
                        fontWeight = FontWeight.Black
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DeepSpace
                )
            )
        },
        containerColor = DeepSpace
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(DeepSpace)
        ) {
            // Search bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = onSearchChange,
                placeholder = {
                    Text("Buscar personaje…", color = MutedGray)
                },
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = null, tint = YellowSW)
                },
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor   = YellowSW,
                    unfocusedBorderColor = SpaceGrayLight,
                    focusedTextColor     = StarWhite,
                    unfocusedTextColor   = StarWhite,
                    cursorColor          = YellowSW,
                    focusedContainerColor   = SpaceGray,
                    unfocusedContainerColor = SpaceGray
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )

            // Content area
            when (uiState) {
                is PeopleUiState.Loading -> LoadingScreen()

                is PeopleUiState.Error -> ErrorScreen(
                    message = uiState.message,
                    onRetry = onRetry
                )

                is PeopleUiState.Success -> {
                    val people = uiState.people
                    if (people.isEmpty()) {
                        EmptyState(searchQuery)
                    } else {
                        // Counter badge
                        Text(
                            text = "${people.size} personaje${if (people.size != 1) "s" else ""}",
                            style = MaterialTheme.typography.labelSmall,
                            color = MutedGray,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                        )

                        LazyColumn(
                            contentPadding = PaddingValues(
                                start = 16.dp, end = 16.dp,
                                top = 4.dp, bottom = 24.dp
                            ),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            items(
                                items = people,
                                key   = { it.url }
                            ) { person ->
                                PersonCard(
                                    person  = person,
                                    onClick = { onPersonClick(person) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
