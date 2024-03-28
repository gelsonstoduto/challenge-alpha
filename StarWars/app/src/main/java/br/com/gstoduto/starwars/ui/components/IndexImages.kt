package br.com.gstoduto.starwars.ui.components

import androidx.compose.runtime.Composable
import br.com.gstoduto.starwars.model.Movie
import br.com.gstoduto.starwars.model.Vehicle

@Composable
fun getIndexMovieImage(movie: Movie) = movie.url
    .substring(movie.url.length - 4)
    .split("/")
    .drop(1)
    .joinToString("")

@Composable
fun getIndexVehicleImage(vehicle: Vehicle) = vehicle.url
    .substring(vehicle.url.length - 4)
    .split("/")
    .drop(1)
    .joinToString("")