package com.any.musica.data.android

import android.database.Cursor

interface ContentResolverQuery {
    fun getMusicCursor(): Cursor?
}