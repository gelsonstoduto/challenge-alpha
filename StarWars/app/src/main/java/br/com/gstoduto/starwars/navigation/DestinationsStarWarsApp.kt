package br.com.gstoduto.starwars.navigation

sealed class DestinationsStarWarsApp(val rota: String) {
    object homeRoute : DestinationsStarWarsApp("home")
    object myListRoute : DestinationsStarWarsApp("myList")
}