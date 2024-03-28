package br.com.gstoduto.starwars.ui.viewmodels.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.gstoduto.starwars.database.entities.toMovie
import br.com.gstoduto.starwars.model.Movie
import br.com.gstoduto.starwars.ui.uistates.movie.MyMovieListUiState
import br.com.gstoduto.starwars.ui.use_case.movie.GetMyMovieListUseCaseImpl
import br.com.gstoduto.starwars.ui.use_case.movie.RemoveMovieFromMyListUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyMovieListViewModel @Inject constructor(
    private val getMyMovieListUseCase: GetMyMovieListUseCaseImpl,
    private val removeMovieFromMyListUseCase: RemoveMovieFromMyListUseCaseImpl
) : ViewModel() {

    private val _uiState = MutableStateFlow<MyMovieListUiState>(
        MyMovieListUiState.Loading
    )
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            loadMyList()
        }
    }

    private suspend fun loadUiState() {
        val movies = mutableListOf<Movie>()
        getMyMovieListUseCase.getMyListMovies().collect {
            it.listIterator().forEach { movieEntity ->
                movies.add(movieEntity.toMovie())
            }
            _uiState.update {
                if (movies.isEmpty()) {
                    MyMovieListUiState.Empty(movies = movies)
                } else {
                    MyMovieListUiState.Success(movies = movies)
                }
            }
        }
    }

    suspend fun removeFromMyList(movie: Movie) {
        removeMovieFromMyListUseCase.removeMovieFromMyList(movie.title)
    }

    suspend fun loadMyList() {
        loadUiState()
    }

}