package br.com.gstoduto.starwars.network.services

import br.com.gstoduto.starwars.network.model.FilmsResponse
import retrofit2.http.GET

interface MovieService {

    @GET("films")
    suspend fun findAll(): FilmsResponse
}