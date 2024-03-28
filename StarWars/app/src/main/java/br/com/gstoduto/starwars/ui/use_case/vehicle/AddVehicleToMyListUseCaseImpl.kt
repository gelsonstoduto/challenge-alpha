package br.com.gstoduto.starwars.ui.use_case.vehicle

import br.com.gstoduto.starwars.repositories.MovieRepository
import javax.inject.Inject

class AddVehicleToMyListUseCaseImpl @Inject constructor(
    private val repository: MovieRepository,
): AddVehicleToMyListUseCase {
    override suspend fun addVehicleToMyList(id: String) {
        return repository.addToMyList(id)
    }
}