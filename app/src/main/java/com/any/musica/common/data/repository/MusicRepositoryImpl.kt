package com.any.musica.common.data.repository

import android.content.ContentUris
import android.provider.MediaStore
import com.any.musica.common.data.android.ContentResolverQuery
import com.any.musica.common.domain.model.Music
import com.any.musica.common.domain.repository.MusicRepository
import javax.inject.Inject

class MusicRepositoryImpl @Inject constructor(
    private val contentResolverQuery: ContentResolverQuery
) : MusicRepository {
    override suspend fun getAll() = buildList {
        val cursor = contentResolverQuery.getMusicCursor() ?: return@buildList
        val id = cursor.getColumnIndex(MediaStore.Audio.AudioColumns._ID)
        val name = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.DISPLAY_NAME)

        while (cursor.moveToNext()) {
            add(
                Music(
                    id = cursor.getLong(id),
                    displayName = cursor.getString(name)
                )
            )
        }
    }
}