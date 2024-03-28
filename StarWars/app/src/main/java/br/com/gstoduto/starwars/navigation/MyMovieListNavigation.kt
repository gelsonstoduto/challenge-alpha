package br.com.gstoduto.starwars.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import br.com.gstoduto.starwars.model.Movie
import br.com.gstoduto.starwars.ui.screens.movie.MyListScreen
import br.com.gstoduto.starwars.ui.viewmodels.movie.MyMovieListViewModel
import kotlinx.coroutines.launch

fun NavGraphBuilder.myMovieListScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToMovieDetails: (Movie) -> Unit
) {
    composable(DestinationsStarWarsApp.myMovieListRoute.rota) {
        val viewModel = hiltViewModel<MyMovieListViewModel>()
        val uiState by viewModel.uiState.collectAsState()
        val scope = rememberCoroutineScope()
        MyListScreen(
            uiState = uiState,
            onSeeOtherMovies = onNavigateToHome,
            onRemoveMovieFromMyList = {
                scope.launch {
                    viewModel.removeFromMyList(it)
                }
            },
            onMovieClick = onNavigateToMovieDetails,
        )
    }
}

fun NavController.navigateToMyList(
    navOptions: NavOptions? = null
) {
    navigate(DestinationsStarWarsApp.myMovieListRoute.rota, navOptions)
}