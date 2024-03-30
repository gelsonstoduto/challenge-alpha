package br.com.gstoduto.starwars.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import br.com.gstoduto.starwars.ui.components.BottomAppBarItem

@Composable
fun StarWarsNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = DestinationsStarWarsApp.movieRoute.rota
    ) {
        movieScreen(
            onNavigateToMovieDetails = { movie ->
                navController.currentDestination
                BottomAppBarItem.Vehicle.icon
                navController.navigateToMovieDetails(movie.id.toString())
            },
        )
        navController.currentBackStackEntry?.savedStateHandle?.let {
            movieDetails(it)
        }
        myMovieListScreen(
            onNavigateToMovie = {
                navController.navigateToMovie(navOptions {
                    popUpTo(navController.graph.findStartDestination().id) {
                        inclusive = true
                    }
                })
            },
            onNavigateToMovieDetails = {
                navController.navigateToMovieDetails(it.id.toString())
            }
        )
        vehicleScreen(
            onNavigateToVehicleDetails = { vehicle ->
                navController.navigateToVehicleDetails(vehicle.id.toString())
            },
        )
        navController.currentBackStackEntry?.savedStateHandle?.let {
            vehicleDetails(it)
        }
        myVehicleListScreen(
            onNavigateToVehicle = {
                navController.navigateToVehicle(navOptions {
                    popUpTo(navController.graph.findStartDestination().id) {
                        inclusive = true
                    }
                })
            },
            onNavigateToVehicleDetails = {
                navController.navigateToVehicleDetails(it.id.toString())
            }
        )
        specieScreen(
            onNavigateToSpecieDetails = { specie ->
                navController.navigateToSpecieDetails(specie.id.toString())
            },
        )
    }
}

fun NavHostController.navigateToMovieDetails(idMovie: String) {
    navigateDirectly("${DestinationsStarWarsApp.MovieDetails.rota}/$idMovie")
}

fun NavHostController.navigateToVehicleDetails(idVehicle: String) {
    navigateDirectly("${DestinationsStarWarsApp.VehicleDetails.rota}/$idVehicle")
}

fun NavHostController.navigateToSpecieDetails(idSpecie: String) {
    navigateDirectly("${DestinationsStarWarsApp.SpecieDetails.rota}/$idSpecie")
}

fun NavHostController.navigateDirectly(rota: String) = this.navigate(rota) {
    popUpTo(this@navigateDirectly.graph.findStartDestination().id) {
        saveState = true
    }
    launchSingleTop = true
    restoreState = true
}

fun NavController.navigateToBottomAppBarItem(
    item: BottomAppBarItem,
) {
    when (item) {
        BottomAppBarItem.Movie -> {
            navigateToMovie(
                navOptions {
                    launchSingleTop = true
                    popUpTo(DestinationsStarWarsApp.movieRoute.rota)
                }
            )
        }
        BottomAppBarItem.MyMovieList -> {
            navigateToMyMovieList(
                navOptions {
                    launchSingleTop = true
                    popUpTo(DestinationsStarWarsApp.myMovieListRoute.rota)
                }
            )
        }
        BottomAppBarItem.Vehicle -> {
            navigateToVehicle(
                navOptions {
                    launchSingleTop = true
                    popUpTo(DestinationsStarWarsApp.vehicleRoute.rota)
                }
            )
        }
        BottomAppBarItem.MyVehicleList -> {
            navigateToMyVehicleList(
                navOptions {
                    launchSingleTop = true
                    popUpTo(DestinationsStarWarsApp.myVehicleListRoute.rota)
                }
            )
        }
        BottomAppBarItem.Vehicle -> {
            navigateToSpecie(
                navOptions {
                    launchSingleTop = true
                    popUpTo(DestinationsStarWarsApp.specieRoute.rota)
                }
            )
        }

        else -> {
            navigateToMovie(
                navOptions {
                    launchSingleTop = true
                    popUpTo(DestinationsStarWarsApp.movieRoute.rota)
                }
            )
        }
    }
}
