package br.com.gstoduto.starwars.repositories

import android.util.Log
import br.com.gstoduto.starwars.database.dao.VehicleDao
import br.com.gstoduto.starwars.database.entities.VehicleEntity
import br.com.gstoduto.starwars.model.Vehicle
import br.com.gstoduto.starwars.network.model.vehicle.toVehicleEntity
import br.com.gstoduto.starwars.network.services.VehicleService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.net.ConnectException
import javax.inject.Inject

class VehicleRepository @Inject constructor(
    private val dao: VehicleDao,
    private val service: VehicleService
) {
    suspend fun findVehicles(): Flow<List<Vehicle>> {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = service.findAll()
                val entities = response.results.map { it.toVehicleEntity() }
                dao.saveAll(*entities.toTypedArray())
            } catch (e: ConnectException) {
                Log.e("VehicleRepository", "findVehicles: falha ao conectar na API", e)
            }
        }

        return dao.findAll()
    }

    fun findVehicle(id: String): Flow<VehicleEntity> {
        return dao.findVehicleById(id)
    }

    suspend fun addToMyList(id: String) {
        dao.addToMyList(id)
    }

    suspend fun removeFromMyList(id: String) {
        dao.removeFromMyList(id)
    }

    fun myList(): Flow<List<VehicleEntity>>{
        return dao.myList()
    }
}
