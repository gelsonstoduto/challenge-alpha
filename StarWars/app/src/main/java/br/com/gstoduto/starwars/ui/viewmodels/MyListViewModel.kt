package br.com.gstoduto.starwars.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.gstoduto.starwars.database.entities.toMovie
import br.com.gstoduto.starwars.model.Movie
import br.com.gstoduto.starwars.repositories.MovieRepository
import br.com.gstoduto.starwars.ui.uistates.MyListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyListViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<MyListUiState>(
        MyListUiState.Loading
    )
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            loadMyList()
        }
    }

    private suspend fun loadUiState() {
        val movies = mutableListOf<Movie>()
        repository.myList().collect {
            it.listIterator().forEach { movieEntity ->
                movies.add(movieEntity.toMovie())
            }
            _uiState.update {
                if (movies.isEmpty()) {
                    MyListUiState.Empty(movies = movies)
                } else {
                    MyListUiState.Success(movies = movies)
                }
            }
        }
    }

    suspend fun removeFromMyList(movie: Movie) {
        repository.removeFromMyList(movie.title)
    }

    suspend fun loadMyList() {
        loadUiState()
    }

}