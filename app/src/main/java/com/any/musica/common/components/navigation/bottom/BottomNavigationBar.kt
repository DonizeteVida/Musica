package com.any.musica.common.components.navigation.bottom

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.any.musica.R
import com.any.musica.common.components.navigation.main.MainDestiny

sealed interface BottomNavigationBarItems {

    val stringId: Int

    val selectedIcon: ImageVector

    val unselectedIcon: ImageVector

    val destiny: MainDestiny

    data object Home : BottomNavigationBarItems {
        override val stringId = R.string.main_bottom_home

        override val selectedIcon = Icons.Filled.Home

        override val unselectedIcon = Icons.Outlined.Home

        override val destiny = MainDestiny.Home
    }

    data object Search : BottomNavigationBarItems {
        override val stringId = R.string.main_bottom_search

        override val selectedIcon = Icons.Filled.Favorite

        override val unselectedIcon = Icons.Outlined.FavoriteBorder

        override val destiny = MainDestiny.Search
    }

    data object Library : BottomNavigationBarItems {
        override val stringId = R.string.main_bottom_library

        override val selectedIcon = Icons.Filled.Star

        override val unselectedIcon = Icons.Outlined.StarOutline

        override val destiny = MainDestiny.Library
    }
}

private val ITEMS = arrayOf(
    BottomNavigationBarItems.Home,
    BottomNavigationBarItems.Search,
    BottomNavigationBarItems.Library
)

@Composable
fun BottomNavigationBar(
    onItemSelected: (MainDestiny) -> Unit
) {
    var selectedItem by remember { mutableIntStateOf(0) }

    LaunchedEffect(selectedItem) {
        onItemSelected(ITEMS[selectedItem].destiny)
    }

    NavigationBar {
        ITEMS.forEachIndexed { index, item ->
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