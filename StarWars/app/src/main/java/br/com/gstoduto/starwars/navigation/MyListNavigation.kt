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
import br.com.gstoduto.starwars.ui.screens.MyListScreen
import br.com.gstoduto.starwars.ui.viewmodels.MyListViewModel
import kotlinx.coroutines.launch

fun NavGraphBuilder.myListScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToMovieDetails: (Movie) -> Unit
) {
    composable(DestinationsStarWarsApp.myListRoute.rota) {
        val viewModel = hiltViewModel<MyListViewModel>()
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
/*            onRetryLoadMyList = {
                scope.launch {
                    viewModel.loadMyList()
                }
            }*/
        )
    }
}

fun NavController.navigateToMyList(
    navOptions: NavOptions? = null
) {
    navigate(DestinationsStarWarsApp.myListRoute.rota, navOptions)
}