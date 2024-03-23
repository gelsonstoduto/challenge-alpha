package br.com.gstoduto.starwars.ui.uistates

import br.com.gstoduto.starwars.model.Movie


sealed class HomeUiState {

    object Loading : HomeUiState()

    object Empty : HomeUiState()

    data class Success(
        val movies: List<Movie> = emptyList()
    ) : HomeUiState()

}