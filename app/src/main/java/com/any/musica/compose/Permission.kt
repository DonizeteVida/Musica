package com.any.musica.compose

import android.os.Build
import androidx.compose.runtime.Composable
import com.any.musica.compose.android.PermissionRequester

@Composable
fun MusicaRequestPermissions() {
    PermissionRequester(
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            listOf(
                android.Manifest.permission.POST_NOTIFICATIONS,
                android.Manifest.permission.READ_MEDIA_AUDIO
            )
        } else {
            listOf(
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
        }
    )
}