package br.com.gstoduto.starwars.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.gstoduto.starwars.model.Vehicle

@Entity(tableName = "vehicles")
class VehicleEntity(
    @PrimaryKey
    val name: String,
    val model: String,
    val manufacturer: String,
    @ColumnInfo(name = "vehicle_class") var vehicleClass: String?  = null,
    @ColumnInfo(name = "cost_in_credits") var costInCredits: String?  = null,
    @ColumnInfo(name = "max_atmosphering_speed") var maxAtmospheringSpeed: String?  = null,
    val length: String,
    val passengers: String,
    val url: String,
    val inMyList: Boolean = false
)

fun VehicleEntity.toVehicle() = Vehicle(
    name = name,
    model = model,
    manufacturer = manufacturer,
    vehicleClass = vehicleClass,
    costInCredits = costInCredits,
    maxAtmospheringSpeed = maxAtmospheringSpeed,
    length = length,
    passengers = passengers,
    url = url,
    inMyList = inMyList
)
