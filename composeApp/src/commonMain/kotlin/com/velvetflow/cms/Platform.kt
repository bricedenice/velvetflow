package com.velvetflow.cms

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

// In commonMain/kotlin/com/velvetflow/Platform.kt
expect fun getEnvVariable(key: String): String?