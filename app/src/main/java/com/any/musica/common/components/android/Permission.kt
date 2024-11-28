package com.any.musica.common.components.android

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext

@Composable
fun PermissionRequester(
    vararg permissions: List<String>
) {
    val context = LocalContext.current
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = onResult@{ isGranted ->
            if (isGranted.values.all { it }) return@onResult
            context.startActivity(
                Intent(
                    Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                ).setData(
                    Uri.fromParts("package", context.packageName, null)
                )
            )
        }
    )

    LaunchedEffect(permissions) {
        permissions
            .flatMap { it }
            .toTypedArray().also(permissionLauncher::launch)
    }
}