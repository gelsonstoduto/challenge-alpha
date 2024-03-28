package br.com.gstoduto.starwars.ui.use_case.vehicle

import br.com.gstoduto.starwars.database.entities.VehicleEntity
import kotlinx.coroutines.flow.Flow

interface GetVehicleUseCase {
    suspend fun findVehicle(id: String): Flow<VehicleEntity>
}