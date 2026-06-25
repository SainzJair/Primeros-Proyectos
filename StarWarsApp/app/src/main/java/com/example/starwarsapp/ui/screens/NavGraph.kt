package com.example.starwarsapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.starwarsapp.viewmodel.PeopleViewModel

// ── Routes ────────────────────────────────────────────────────────────────────

object Routes {
    const val HOME   = "home"
    const val DETAIL = "detail"
}

// ── Nav Graph ─────────────────────────────────────────────────────────────────

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController(),
    viewModel: PeopleViewModel = viewModel()
) {
    val uiState     by viewModel.uiState.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val selected    by viewModel.selectedPerson.collectAsState()

    NavHost(
        navController    = navController,
        startDestination = Routes.HOME
    ) {
        composable(Routes.HOME) {
            HomeScreen(
                uiState       = uiState,
                searchQuery   = searchQuery,
                onSearchChange = viewModel::onSearchQueryChange,
                onPersonClick = { person ->
                    viewModel.selectPerson(person)
                    navController.navigate(Routes.DETAIL)
                },
                onRetry = viewModel::fetchPeople
            )
        }

        composable(Routes.DETAIL) {
            val person = selected
            if (person != null) {
                DetailScreen(
                    person = person,
                    onBack = {
                        viewModel.clearSelection()
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}
