package com.any.musica

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.any.musica.common.components.MusicaRequestPermissions
import com.any.musica.common.components.navigation.bottom.BottomNavigationBar
import com.any.musica.common.components.navigation.main.MainNavigationHost
import com.any.musica.ui.theme.MusicaTheme

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

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigationBar { mainDestiny ->
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
        MainNavigationHost(
            Modifier.padding(innerPadding),
            navController
        )
    }
}

@Preview
@Composable
private fun MainScreenPrev() {
    MusicaTheme {
        MainScreen()
    }
}