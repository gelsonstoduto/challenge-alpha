package br.com.gstoduto.starwars.ui.use_case.vehicle

import br.com.gstoduto.starwars.repositories.MovieRepository
import javax.inject.Inject

class RemoveVehicleFromMyListUseCaseImpl @Inject constructor(
    private val repository: MovieRepository,
): RemoveVehicleFromMyListUseCase {
    override suspend fun removeVehicleFromMyList(id: String) {
        return repository.removeFromMyList(id)
    }
}