package com.any.musica.common.data.android

import android.database.Cursor

interface ContentResolverQuery {
    suspend fun getMusicCursor(): Cursor?
}