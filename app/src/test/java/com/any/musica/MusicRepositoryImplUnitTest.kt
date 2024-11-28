package com.any.musica

import android.database.Cursor
import android.provider.MediaStore
import com.any.musica.common.data.android.ContentResolverQuery
import com.any.musica.common.data.repository.MusicRepositoryImpl
import com.any.musica.common.domain.model.Music
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.doReturnConsecutively
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class MusicRepositoryImplUnitTest {
    companion object {
        const val ID = MediaStore.Audio.AudioColumns._ID
        const val ID_COLUMN = 1
        const val FAKE_ID = 42L
        const val DISPLAY_NAME = MediaStore.Audio.AudioColumns.DISPLAY_NAME
        const val DISPLAY_NAME_COLUMN = 2
        const val FAKE_DISPLAY_NAME = "my_file.mp3"
    }

    @Test
    fun `it parses a single cursor row`() = runTest {
        val cursor = mock<Cursor> {
            on { moveToNext() } doReturn true doReturn false
            on { getColumnIndex(ID) } doReturn ID_COLUMN
            on { getLong(ID_COLUMN) } doReturn FAKE_ID
            on { getColumnIndex(DISPLAY_NAME) } doReturn DISPLAY_NAME_COLUMN
            on { getString(DISPLAY_NAME_COLUMN) } doReturn FAKE_DISPLAY_NAME
        }
        val contentResolverQuery = mock<ContentResolverQuery> {
            onBlocking { getMusicCursor() } doReturn cursor
        }
        val target = MusicRepositoryImpl(contentResolverQuery)

        val actual = target.getAll().first()
        val expected = Music(id = FAKE_ID, displayName = FAKE_DISPLAY_NAME)
        assertEquals(expected, actual)

        verify(cursor, times(2)).moveToNext()
        verify(cursor, times(1)).getColumnIndex(ID)
        verify(cursor, times(1)).getLong(ID_COLUMN)
        verify(cursor, times(1)).getColumnIndex(DISPLAY_NAME)
        verify(cursor, times(1)).getString(DISPLAY_NAME_COLUMN)
    }

    @Test
    fun `it parses a multiple cursor row`() = runTest {
        val n = (2..100).random()

        val cursor = mock<Cursor> {
            on { moveToNext() } doReturnConsecutively List(n) { true } + false
            on { getColumnIndex(ID) } doReturn ID_COLUMN
            on { getLong(ID_COLUMN) } doReturnConsecutively List(n) { it * FAKE_ID }
            on { getColumnIndex(DISPLAY_NAME) } doReturn DISPLAY_NAME_COLUMN
            on { getString(DISPLAY_NAME_COLUMN) } doReturnConsecutively List(n) { "$FAKE_DISPLAY_NAME$it" }
        }
        val contentResolverQuery = mock<ContentResolverQuery> {
            onBlocking { getMusicCursor() } doReturn cursor
        }
        val target = MusicRepositoryImpl(contentResolverQuery)

        val actual = target.getAll()
        val expected = List(n) { Music(id = it * FAKE_ID, displayName = "$FAKE_DISPLAY_NAME$it") }
        assertEquals(expected, actual)

        verify(cursor, times(n + 1)).moveToNext()
        verify(cursor, times(1)).getColumnIndex(ID)
        verify(cursor, times(n)).getLong(ID_COLUMN)
        verify(cursor, times(1)).getColumnIndex(DISPLAY_NAME)
        verify(cursor, times(n)).getString(DISPLAY_NAME_COLUMN)
    }
}