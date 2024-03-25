package br.com.gstoduto.starwars.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ItemsMenuLateral(
    val icon: ImageVector,
    val title: String,
    val route:String
) {
    object ItemMovies: ItemsMenuLateral(
        Icons.Outlined.Add,
        "Lista de Filmes",
        DestinationsStarWarsApp.homeRoute.rota
    )
}

data class DrawerItems(
    val icon: ImageVector,
    val text: String,
    val route: String,
)
