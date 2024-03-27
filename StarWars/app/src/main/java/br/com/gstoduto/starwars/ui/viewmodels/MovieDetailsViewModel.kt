package br.com.gstoduto.starwars.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.gstoduto.starwars.database.entities.toMovie
import br.com.gstoduto.starwars.model.Movie
import br.com.gstoduto.starwars.repositories.MovieRepository
import br.com.gstoduto.starwars.ui.uistates.MovieDetailsUiState
import br.com.gstoduto.starwars.util.Constants.ID_MOVIE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: MovieRepository
) : ViewModel() {

    private val idMovie = savedStateHandle.get<String>(ID_MOVIE)

    private val _uiState = MutableStateFlow(MovieDetailsUiState())
    val uiState: StateFlow<MovieDetailsUiState>
        get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            idMovie?.let { loadMovie(it) }
        }
    }

    private suspend fun loadMovie(title: String) {
        title.let {
            val movie = repository.findMovie(it)

            movie.collect { movieEntity ->
                _uiState.value = _uiState.value.copy(
                    movie = movieEntity.toMovie()
                )
            }
        }
    }

    suspend fun addToMyList(movie: Movie) {
        repository.addToMyList(movie.title)
    }

    suspend fun removeFromMyList(movie: Movie) {
        repository.removeFromMyList(movie.title)
    }
}
