package com.velvetflow.cms

class WasmPlatform : Platform {
    override val name: String = "Web with Kotlin/Wasm"
}

actual fun getPlatform(): Platform = WasmPlatform()

// Refactor js("process.env[key]") into a top-level function
private fun getJSVariable(key: String): Any? {
    return js("process.env[key]")
}

// Update getEnvVariable to call the refactored function
actual fun getEnvVariable(key: String): String? =
    getJSVariable(key) as? String