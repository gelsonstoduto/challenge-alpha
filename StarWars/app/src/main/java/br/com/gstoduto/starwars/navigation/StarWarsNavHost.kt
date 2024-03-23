package br.com.gstoduto.starwars.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
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
        homeScreen(
            onNavigateToMovieDetails = {

            },
        )
    }
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

        }
    }
}
