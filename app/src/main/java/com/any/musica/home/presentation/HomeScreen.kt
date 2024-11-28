package com.any.musica.home.presentation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.any.musica.ui.theme.MusicaTheme

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    HomeScreen(viewModel.state)
}

@Composable
private fun HomeScreen(
    state: HomeScreenState
) {
    if (state.isLoading) {
        CircularProgressIndicator()
        return
    }
    LazyColumn {
        items(state.musics) {
            Text(it.displayName)
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    MusicaTheme {
        HomeScreen(HomeScreenState())
    }
}