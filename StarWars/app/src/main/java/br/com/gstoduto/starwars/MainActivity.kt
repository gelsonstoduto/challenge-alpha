package br.com.gstoduto.starwars

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.com.gstoduto.starwars.navigation.DestinationsStarWarsApp
import br.com.gstoduto.starwars.navigation.StarWarsNavHost
import br.com.gstoduto.starwars.navigation.navigateToBottomAppBarItem
import br.com.gstoduto.starwars.ui.components.BottomAppBarItem
import br.com.gstoduto.starwars.ui.components.MenuLateral
import br.com.gstoduto.starwars.ui.components.StarWarsBottomAppBar
import br.com.gstoduto.starwars.ui.theme.StarWarsTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StarWarsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    StarWarsApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StarWarsApp(
    navController: NavHostController = rememberNavController()
) {
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination
    val currentRoute = currentDestination?.route

    val selectedItem by remember(currentDestination) {
        val item = when (currentRoute) {
            DestinationsStarWarsApp.homeRoute.rota -> BottomAppBarItem.Home
            DestinationsStarWarsApp.myMovieListRoute.rota -> BottomAppBarItem.MyList
            else -> BottomAppBarItem.Home
        }
        mutableStateOf(item)
    }

    val bottomAppBarItems = remember {
        listOf(
            BottomAppBarItem.Home,
            BottomAppBarItem.MyList
        )
    }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    MenuLateral(
        navController = navController,
        drawerState = drawerState,
        currentRoute = currentRoute
    ) {
        StarWarsApp(
            bottomAppBarItems = bottomAppBarItems,
            selectedItem = selectedItem,
            onBottomAppBarItemSelected = { item ->
                navController.navigateToBottomAppBarItem(item)
            },
            drawerState = drawerState,
        )
        {
            StarWarsNavHost(
                navController = navController
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StarWarsApp(
    selectedItem: BottomAppBarItem,
    bottomAppBarItems: List<BottomAppBarItem>,
    onBottomAppBarItemSelected: (BottomAppBarItem) -> Unit,
    drawerState: DrawerState,
    content: @Composable () -> Unit
) {
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "StarWars"
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            drawerState.open()
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Menu,
                            contentDescription = null
                        )
                    }
                }
            )
        },
        bottomBar = {
            StarWarsBottomAppBar(
                selectedItem = selectedItem,
                items = bottomAppBarItems,
                onItemClick = {
                    onBottomAppBarItemSelected(it)
                }
            )
        }
    ) {
        Box(
            Modifier.padding(it)
        ) {
            content()
        }
    }
}

