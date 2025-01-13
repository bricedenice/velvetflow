package com.velvetflow.cms

import platform.UIKit.UIDevice

// In iosMain/kotlin/com/velvetflow/Platform.ios.kt
import platform.Foundation.NSProcessInfo
actual fun getEnvVariable(key: String): String? =
    NSProcessInfo.processInfo.environment[key] as? String

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

