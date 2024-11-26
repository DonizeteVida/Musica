package com.any.musica.data.android

import android.content.Context
import android.database.Cursor
import android.os.CancellationSignal
import android.provider.MediaStore
import androidx.core.content.ContentResolverCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ContentResolverQueryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ContentResolverQuery {
    override fun getMusicCursor(): Cursor? {
        val projection = arrayOf(
            MediaStore.Audio.AudioColumns.IS_MUSIC,
            MediaStore.Audio.AudioColumns._ID
        )
        val signal: CancellationSignal? = null
        return ContentResolverCompat.query(
            context.contentResolver, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            null,
            signal
        )
    }
}