package com.any.musica.data.android

import android.content.Context
import android.database.Cursor
import android.os.CancellationSignal
import android.provider.MediaStore
import androidx.core.content.ContentResolverCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject

class ContentResolverQueryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ContentResolverQuery {
    override suspend fun getMusicCursor() = suspendCancellableCoroutine<Cursor?> {
        val signal = CancellationSignal()
        it.invokeOnCancellation { signal.cancel() }

        val projection = arrayOf(
            MediaStore.Audio.AudioColumns.IS_MUSIC,
            MediaStore.Audio.AudioColumns._ID
        )

        ContentResolverCompat.query(
            context.contentResolver,
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            null,
            signal
        )
    }
}