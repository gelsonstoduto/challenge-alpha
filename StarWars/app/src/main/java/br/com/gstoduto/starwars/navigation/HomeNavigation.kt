package br.com.gstoduto.starwars.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import br.com.gstoduto.starwars.model.Movie
import br.com.gstoduto.starwars.ui.screens.HomeScreen
import br.com.gstoduto.starwars.ui.viewmodels.HomeViewModel

fun NavGraphBuilder.homeScreen(
    onNavigateToMovieDetails: (Movie) -> Unit,
) {
    composable(DestinationsStarWarsApp.homeRoute.rota) {
        val viewModel = hiltViewModel<HomeViewModel>()
        val uiState by viewModel.uiState.collectAsState()
        HomeScreen(
            uiState = uiState,
            columns = 2,
            onMovieClick = onNavigateToMovieDetails,
            onRetryLoadMovies = {
                viewModel.loadMovies()
            }
        )
    }
}

fun NavController.navigateToHome(
    navOptions: NavOptions? = null
) {
    navigate(DestinationsStarWarsApp.homeRoute.rota, navOptions)
}