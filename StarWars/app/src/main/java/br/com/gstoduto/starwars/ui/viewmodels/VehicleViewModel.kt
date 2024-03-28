package br.com.gstoduto.starwars.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.gstoduto.starwars.repositories.VehicleRepository
import br.com.gstoduto.starwars.ui.uistates.VehicleUiState
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
class VehicleViewModel @Inject constructor(
    private val repository: VehicleRepository
) : ViewModel() {

    private var currentUiStateJob: Job? = null
    private val _uiState = MutableStateFlow<VehicleUiState>(
        VehicleUiState.Loading
    )
    val uiState = _uiState.asStateFlow()

    init {
        loadUiState()
    }

    private fun loadUiState() {
        currentUiStateJob?.cancel()
        currentUiStateJob = viewModelScope.launch {
            repository.findVehicles().onStart {
                _uiState.update { VehicleUiState.Loading }
            }.collectLatest { vehicles ->
                if (vehicles.isEmpty()) {
                    _uiState.update {
                        VehicleUiState.Empty
                    }
                } else {
                    _uiState.update {
                        VehicleUiState.Success(
                            vehicles = vehicles
                        )
                    }
                }
            }
        }
    }

    fun loadVehicles() {
        loadUiState()
    }
}