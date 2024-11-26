package com.any.musica.domain.repository

import com.any.musica.domain.model.Music

interface MusicRepository {
    suspend fun getAll(): List<Music>
}