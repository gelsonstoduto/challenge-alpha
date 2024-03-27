package br.com.gstoduto.starwars.ui.uistates

import br.com.gstoduto.starwars.model.Movie

sealed class MyListUiState {

    object Loading : MyListUiState()

    data class Empty(
        val movies: List<Movie> = emptyList()
    ) : MyListUiState()

    data class Success(
        val movies: List<Movie> = emptyList()
    ) : MyListUiState()

}

