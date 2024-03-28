package br.com.gstoduto.starwars.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument
import br.com.gstoduto.starwars.util.Constants.ID_MOVIE

sealed class DestinationsStarWarsApp(val rota: String) {
    object movieRoute : DestinationsStarWarsApp("movie")
    object myMovieListRoute : DestinationsStarWarsApp("myMovieList")

    object MovieDetails {
        const val rota = "movieDetails"
        const val routeWithArguments = "$rota/{$ID_MOVIE}"
        val arguments = listOf(
            navArgument(ID_MOVIE) {
                defaultValue = ""
                type = NavType.StringType
            }
        )
    }

    object vehicleRoute : DestinationsStarWarsApp("vehicle")
    object myVehicleListRoute : DestinationsStarWarsApp("myVehicleList")
}