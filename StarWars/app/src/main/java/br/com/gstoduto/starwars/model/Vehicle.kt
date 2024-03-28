package br.com.gstoduto.starwars.model

import androidx.room.ColumnInfo

data class Vehicle(
    val name: String,
    val model: String,
    val manufacturer: String,
    @ColumnInfo(name = "vehicle_class") var vehicleClass: String?  = null,
    @ColumnInfo(name = "cost_in_credits") var costInCredits: String?  = null,
    @ColumnInfo(name = "max_atmosphering_speed") var maxAtmospheringSpeed: String?  = null,
    val length: String,
    @ColumnInfo(name = "cargo_capacity") var cargoCapacity: String?  = null,
    @ColumnInfo(name = "consumables", defaultValue = "") var consumables: String?  = null,
    val passengers: String,
    val url: String,
    val inMyList: Boolean = false
)