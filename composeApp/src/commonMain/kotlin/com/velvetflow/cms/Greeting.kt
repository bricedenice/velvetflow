package com.velvetflow.cms

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }

    fun greetWithParams(name: String): String {
        return "Hello, $name!"
    }
}