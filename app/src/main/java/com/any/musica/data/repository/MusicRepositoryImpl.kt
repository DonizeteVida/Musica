package com.any.musica.data.repository

import android.provider.MediaStore
import com.any.musica.data.android.ContentResolverQuery
import com.any.musica.domain.model.Music
import com.any.musica.domain.repository.MusicRepository
import javax.inject.Inject

class MusicRepositoryImpl @Inject constructor(
    private val contentResolverQuery: ContentResolverQuery
) : MusicRepository {
    override suspend fun getAll() = buildList {
        val cursor = contentResolverQuery.getMusicCursor() ?: return@buildList
        val id = cursor.getColumnIndex(MediaStore.Audio.AudioColumns._ID)

        while (cursor.moveToNext()) {
            add(Music(id = cursor.getString(id)))
        }
    }
}