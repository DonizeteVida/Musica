package com.any.musica.home.presentation

import com.any.musica.common.domain.model.Music

data class HomeScreenState(
    val isLoading: Boolean = true,
    val musics: List<Music> = emptyList()
)