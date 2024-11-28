package com.any.musica.common.components

import android.Manifest
import android.os.Build
import androidx.compose.runtime.Composable
import com.any.musica.common.components.android.PermissionRequester

@Composable
fun RequestPermissions() {
    PermissionRequester(
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            listOf(
                Manifest.permission.POST_NOTIFICATIONS,
                Manifest.permission.READ_MEDIA_AUDIO
            )
        } else {
            listOf(
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        }
    )
}