package br.com.gstoduto.starwars.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.gstoduto.starwars.repositories.MovieRepository
import br.com.gstoduto.starwars.ui.uistates.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private var currentUiStateJob: Job? = null
    private val _uiState = MutableStateFlow<HomeUiState>(
        HomeUiState.Loading
    )
    val uiState = _uiState.asStateFlow()

    init {
        loadUiState()
    }

    private fun loadUiState() {
        currentUiStateJob?.cancel()
        currentUiStateJob = viewModelScope.launch {
            repository.findMovies().onStart {
                _uiState.update { HomeUiState.Loading }
            }.collectLatest { movies ->
                if (movies.isEmpty()) {
                    _uiState.update {
                        HomeUiState.Empty
                    }
                } else {
                    _uiState.update {
                        HomeUiState.Success(
                            movies = movies
                        )
                    }
                }
            }
        }
    }

    fun loadMovies() {
        loadUiState()
    }
}