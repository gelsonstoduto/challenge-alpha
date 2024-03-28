package br.com.gstoduto.starwars.network.model

import br.com.gstoduto.starwars.database.entities.VehicleEntity

data class VehicleResponse(
    val name: String,
    val model: String,
    val manufacturer: String,
    val vehicleClass: String,
    val costInCredits: String,
    val maxAtmospheringSpeed: String,
    val length: String,
    val passengers: String,
    val url: String,
    val inMyList: Boolean
)

fun VehicleResponse.toVehicleEntity(): VehicleEntity {
    return VehicleEntity(
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
}