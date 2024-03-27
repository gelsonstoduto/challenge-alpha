package br.com.gstoduto.starwars.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.gstoduto.starwars.ui.screens.MovieDetailsScreen
import br.com.gstoduto.starwars.ui.viewmodels.MovieDetailsViewModel
import br.com.gstoduto.starwars.util.Constants.ID_MOVIE
import kotlinx.coroutines.launch

fun NavGraphBuilder.movieDetails(
    savedStateHandle: SavedStateHandle,
) {
    composable(
        route = DestinationsStarWarsApp.MovieDetails.routeWithArguments,
        arguments = DestinationsStarWarsApp.MovieDetails.arguments
    ) { navBackStackEntry ->
        navBackStackEntry.arguments?.getString(
            ID_MOVIE
        )?.let { idMovie ->

            val viewModel = hiltViewModel<MovieDetailsViewModel>()
            val uiState by viewModel.uiState.collectAsState()

            val scope = rememberCoroutineScope()
            savedStateHandle[ID_MOVIE] = idMovie

            MovieDetailsScreen(
                uiState = uiState,
                onAddToMyListClick = {
                    scope.launch {
                        viewModel.addToMyList(it)
                    }
                },
                onRemoveFromMyList = {
                    scope.launch {
                        viewModel.removeFromMyList(it)
                    }
                }
            )
        }
    }
}