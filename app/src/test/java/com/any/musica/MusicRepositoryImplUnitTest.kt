package com.any.musica

import android.database.Cursor
import android.provider.MediaStore
import com.any.musica.data.android.ContentResolverQuery
import com.any.musica.data.repository.MusicRepositoryImpl
import com.any.musica.domain.model.Music
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class MusicRepositoryImplUnitTest {
    companion object {
        const val ID = MediaStore.Audio.AudioColumns._ID
        const val ID_COLUMN = 1
        const val FAKE_URI = "content://my_music"
    }

    @Test
    fun `it parses a single cursor row`() {
        val cursor = mock<Cursor> {
            on { moveToNext() } doReturn true doReturn false
            on { getColumnIndex(ID) } doReturn ID_COLUMN
            on { getString(ID_COLUMN) } doReturn FAKE_URI
        }
        val contentResolverQuery = mock<ContentResolverQuery> {
            on { getMusicCursor() } doReturn cursor
        }
        val target = MusicRepositoryImpl(contentResolverQuery)

        val actual = target.getAll().first()
        val expected = Music(id = FAKE_URI)
        assertEquals(expected, actual)

        verify(cursor, times(2)).moveToNext()
        verify(cursor, times(1)).getColumnIndex(ID)
        verify(cursor, times(1)).getString(ID_COLUMN)
    }
}