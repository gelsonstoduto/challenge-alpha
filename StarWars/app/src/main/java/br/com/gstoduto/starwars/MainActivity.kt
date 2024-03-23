package br.com.gstoduto.starwars

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.com.gstoduto.starwars.navigation.DestinationsStarWarsApp
import br.com.gstoduto.starwars.navigation.StarWarsNavHost
import br.com.gstoduto.starwars.navigation.navigateToBottomAppBarItem
import br.com.gstoduto.starwars.ui.components.BottomAppBarItem
import br.com.gstoduto.starwars.ui.components.StarWarsBottomAppBar
import br.com.gstoduto.starwars.ui.theme.StarWarsTheme
import dagger.hilt.android.AndroidEntryPoint

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

@Composable
fun StarWarsApp(
    navController: NavHostController = rememberNavController()
) {
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination
    val currentRoute = currentDestination?.route
    val selectedBottomAppBarItem = when (currentRoute) {
        DestinationsStarWarsApp.homeRoute.rota -> BottomAppBarItem.Home
        DestinationsStarWarsApp.myListRoute.rota -> BottomAppBarItem.MyList
        else -> BottomAppBarItem.Home
    }
    val bottomAppBarItems = remember {
        listOf(
            BottomAppBarItem.Home,
            BottomAppBarItem.MyList
        )
    }
    val isShowBackNavigation = when (currentRoute) {
        DestinationsStarWarsApp.myListRoute.rota, DestinationsStarWarsApp.movieDetailsRouteFullpath.rota -> true
        else -> false
    }
    val isShowBottomAppBar = when (currentRoute) {
        DestinationsStarWarsApp.homeRoute.rota, DestinationsStarWarsApp.myListRoute.rota -> true
        else -> false
    }
    StarWarsApp(
        bottomAppBarItems = bottomAppBarItems,
        selectedBottomAppBarItem = selectedBottomAppBarItem,
        onBottomAppBarItemSelected = { item ->
            navController.navigateToBottomAppBarItem(item)
        },
        isShowBackNavigation = isShowBackNavigation,
        isShowBottomAppBar = isShowBottomAppBar,
        onBackNavigationClick = {
            navController.popBackStack()
        },
        topAppBarTitle = {
            when (currentRoute) {
                DestinationsStarWarsApp.myListRoute.rota -> {
                    Text("Minha lista")
                }
                DestinationsStarWarsApp.movieDetailsRouteFullpath.rota -> {
                    Text("Informações")
                }
                DestinationsStarWarsApp.homeRoute.rota -> {
                    Image(
                        painterResource(id = R.drawable.star_wars_logo),
                        contentDescription = null,
                        Modifier
                            .size(30.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    )
    {
        StarWarsNavHost(
            navController = navController
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StarWarsApp(
    selectedBottomAppBarItem: BottomAppBarItem,
    bottomAppBarItems: List<BottomAppBarItem>,
    onBottomAppBarItemSelected: (BottomAppBarItem) -> Unit,
    isShowBackNavigation: Boolean = false,
    isShowBottomAppBar: Boolean = false,
    topAppBarTitle: @Composable () -> Unit = {},
    onBackNavigationClick: () -> Unit = {},
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    topAppBarTitle()
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    navigationIconContentColor = MaterialTheme.colorScheme.onBackground,
                    actionIconContentColor = MaterialTheme.colorScheme.onBackground
                ),
                navigationIcon = {
                    if (isShowBackNavigation) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = null,
                            Modifier
                                .padding(16.dp)
                                .clip(CircleShape)
                                .clickable {
                                    onBackNavigationClick()
                                }
                        )
                    }
                })
        },
        bottomBar = {
            if (isShowBottomAppBar) {
                StarWarsBottomAppBar(
                    selectedItem = selectedBottomAppBarItem,
                    items = bottomAppBarItems,
                    onItemClick = {
                        onBottomAppBarItemSelected(it)
                    }
                )
            }
        }
    ) {
        Box(
            Modifier.padding(it)
        ) {
            content()
        }
    }
}

