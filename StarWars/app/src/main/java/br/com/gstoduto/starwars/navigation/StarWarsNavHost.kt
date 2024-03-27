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
        startDestination = DestinationsStarWarsApp.homeRoute.rota
    ) {
        movieScreen(
            onNavigateToMovieDetails = { movie ->
                navController.navigateToMovieDetails(movie.title)
            },
        )
        navController.currentBackStackEntry?.savedStateHandle?.let {
            movieDetails(it)
        }
        myListScreen(
            onNavigateToHome = {
                navController.navigateToHome(navOptions {
                    popUpTo(navController.graph.findStartDestination().id) {
                        inclusive = true
                    }
                })
            },
            onNavigateToMovieDetails = {
                navController.navigateToMovieDetails(it.title)
            }
        )
    }
}

fun NavHostController.navigateToMovieDetails(idMovie: String) {
    navigateDirectly("${DestinationsStarWarsApp.MovieDetails.rota}/$idMovie")
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
        BottomAppBarItem.Home -> {
            navigateToHome(
                navOptions {
                    launchSingleTop = true
                    popUpTo(DestinationsStarWarsApp.homeRoute.rota)
                }
            )
        }
        BottomAppBarItem.MyList -> {
            navigateToMyList(
                navOptions {
                    launchSingleTop = true
                    popUpTo(DestinationsStarWarsApp.myListRoute.rota)
                }
            )
        }
    }
}
