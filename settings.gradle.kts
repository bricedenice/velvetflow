import org.gradle.kotlin.dsl.maven

rootProject.name = "VelvetFlow"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/ktor/eap")
        maven("https://aws-sdk-kotlin.s3.amazonaws.com/kotlin")
    }
    plugins {
        id("org.jetbrains.kotlin.multiplatform") version "1.8.0"
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/ktor/eap")
        maven("https://aws-sdk-kotlin.s3.amazonaws.com/kotlin")
    }
}

include(":composeApp")