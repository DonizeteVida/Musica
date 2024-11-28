package com.any.musica.home.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.any.musica.common.domain.repository.MusicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val musicRepository: MusicRepository
) : ViewModel() {
    var state by mutableStateOf(HomeScreenState())
        private set

    init {
        getMusics()
    }

    private fun getMusics() = viewModelScope.launch {
        state = state.copy(
            isLoading = false,
            musics = musicRepository.getAll()
        )
    }
}