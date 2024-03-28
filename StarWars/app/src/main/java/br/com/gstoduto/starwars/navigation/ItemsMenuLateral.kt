package br.com.gstoduto.starwars.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Adjust
import androidx.compose.material.icons.outlined.DirectionsCar
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ItemsMenuLateral(
    val icon: ImageVector,
    val title: String,
    val route:String
) {
    object ItemMovies: ItemsMenuLateral(
        Icons.Outlined.Movie,
        "Film List",
        DestinationsStarWarsApp.homeRoute.rota
    )

    object ItemVehicles: ItemsMenuLateral(
        Icons.Outlined.DirectionsCar,
        "Vehicle list",
        DestinationsStarWarsApp.vehicleRoute.rota
    )

    object ItemSpecies: ItemsMenuLateral(
        Icons.Outlined.Adjust,
        "Species list",
        DestinationsStarWarsApp.homeRoute.rota
    )
}

data class DrawerItems(
    val icon: ImageVector,
    val text: String,
    val route: String,
)
