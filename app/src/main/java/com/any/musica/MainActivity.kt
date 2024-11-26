package com.any.musica

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.any.musica.compose.MusicaRequestPermissions
import com.any.musica.ui.theme.MusicaTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MusicaTheme {
                MusicaRequestPermissions()
                MainScreen()
            }
        }
    }
}

sealed interface MainDestiny {
    @Serializable
    data object Home : MainDestiny

    @Serializable
    data object Search : MainDestiny

    @Serializable
    data object Library : MainDestiny
}

sealed interface BottomNavigationItems {

    val stringId: Int

    val selectedIcon: ImageVector

    val unselectedIcon: ImageVector

    val destiny: MainDestiny

    data object Home : BottomNavigationItems {
        override val stringId = R.string.main_bottom_home

        override val selectedIcon = Icons.Filled.Home

        override val unselectedIcon = Icons.Outlined.Home

        override val destiny = MainDestiny.Home
    }

    data object Search : BottomNavigationItems {
        override val stringId = R.string.main_bottom_search

        override val selectedIcon = Icons.Filled.Favorite

        override val unselectedIcon = Icons.Outlined.Favorite

        override val destiny = MainDestiny.Search
    }

    data object Library : BottomNavigationItems {
        override val stringId = R.string.main_bottom_library

        override val selectedIcon = Icons.Filled.Star

        override val unselectedIcon = Icons.Outlined.Star

        override val destiny = MainDestiny.Library
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            MusicaBottomNavigationBar { mainDestiny ->
                navController.navigate(mainDestiny) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = MainDestiny.Home,
            Modifier.padding(innerPadding)
        ) {
            composable<MainDestiny.Home> {
                var counter by remember { mutableIntStateOf(0) }
                LaunchedEffect(Unit) {
                    while (isActive) {
                        counter++
                        delay(1000)
                    }
                }
                Text("Home: $counter")
            }

            composable<MainDestiny.Search> {
                var counter by remember { mutableIntStateOf(0) }
                LaunchedEffect(Unit) {
                    while (isActive) {
                        counter++
                        delay(1000)
                    }
                }
                Text("Search: $counter")
            }

            composable<MainDestiny.Library> {
                var counter by remember { mutableIntStateOf(0) }
                LaunchedEffect(Unit) {
                    while (isActive) {
                        counter++
                        delay(1000)
                    }
                }
                Text("Library: $counter")
            }
        }
    }
}

@Composable
fun MusicaBottomNavigationBar(
    onItemSelected: (MainDestiny) -> Unit
) {
    val items = remember {
        arrayOf(
            BottomNavigationItems.Home,
            BottomNavigationItems.Search,
            BottomNavigationItems.Library
        )
    }
    var selectedItem by remember { mutableIntStateOf(0) }

    LaunchedEffect(selectedItem) {
        onItemSelected(items[selectedItem].destiny)
    }

    NavigationBar {
        items.forEachIndexed { index, item ->
            val description = stringResource(item.stringId)
            val icon = if (selectedItem == index) item.selectedIcon else item.unselectedIcon
            NavigationBarItem(
                icon = { Icon(icon, contentDescription = description) },
                label = { Text(description) },
                selected = selectedItem == index,
                onClick = { selectedItem = index }
            )
        }
    }
}

@Preview
@Composable
private fun MainScreenPrev() {
    MusicaTheme {
        MainScreen()
    }
}