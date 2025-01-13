package com.velvetflow.cms

import android.os.Build

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

// In androidMain/kotlin/com/velvetflow/Platform.android.kt
actual fun getEnvVariable(key: String): String? = System.getenv(key)