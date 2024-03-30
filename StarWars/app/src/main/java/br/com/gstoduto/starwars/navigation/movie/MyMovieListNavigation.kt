package br.com.gstoduto.starwars.navigation.movie

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import br.com.gstoduto.starwars.model.Movie
import br.com.gstoduto.starwars.navigation.DestinationsStarWarsApp
import br.com.gstoduto.starwars.ui.screens.movie.MyMovieListScreen
import br.com.gstoduto.starwars.ui.viewmodels.movie.MyMovieListViewModel
import kotlinx.coroutines.launch

fun NavGraphBuilder.myMovieListScreen(
    onNavigateToMovie: () -> Unit,
    onNavigateToMovieDetails: (Movie) -> Unit
) {
    composable(DestinationsStarWarsApp.myMovieListRoute.rota) {
        val viewModel = hiltViewModel<MyMovieListViewModel>()
        val uiState by viewModel.uiState.collectAsState()
        val scope = rememberCoroutineScope()
        MyMovieListScreen(
            uiState = uiState,
            onSeeOtherMovies = onNavigateToMovie,
            onRemoveMovieFromMyList = {
                scope.launch {
                    viewModel.removeFromMyList(it)
                }
            },
            onMovieClick = onNavigateToMovieDetails,
        )
    }
}

fun NavController.navigateToMyMovieList(
    navOptions: NavOptions? = null
) {
    navigate(DestinationsStarWarsApp.myMovieListRoute.rota, navOptions)
}