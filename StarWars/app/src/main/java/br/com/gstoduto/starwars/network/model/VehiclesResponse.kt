package br.com.gstoduto.starwars.network.model

data class VehiclesResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<VehicleResponse>
)