package com.any.musica.common.domain.repository

import com.any.musica.common.domain.model.Music

interface MusicRepository {
    suspend fun getAll(): List<Music>
}