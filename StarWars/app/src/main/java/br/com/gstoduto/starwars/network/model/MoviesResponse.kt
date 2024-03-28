package br.com.gstoduto.starwars.network.model

data class MoviesResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<MovieResponse>
)