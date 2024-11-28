package com.any.musica.common.components.navigation.main

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.serialization.Serializable

sealed interface MainDestiny {
    @Serializable
    data object Home : MainDestiny

    @Serializable
    data object Search : MainDestiny

    @Serializable
    data object Library : MainDestiny
}

@Composable
fun MainNavigationHost(
    modifier: Modifier,
    navHostController: NavHostController
) {
    NavHost(
        navHostController,
        MainDestiny.Home,
        modifier
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