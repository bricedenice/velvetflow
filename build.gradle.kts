import com.android.kotlin.multiplatform.ide.models.serialization.androidTargetKey
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.jetbrains.kotlin.multiplatform") version "2.1.0"
    id("com.android.application") version "8.5.2"
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

android {
    compileSdkVersion(34)
    buildToolsVersion = "33.0.0"
    namespace = "com.velvetflow.cms"
    defaultConfig {
        applicationId = "com.velvetflow.cms"
        minSdkVersion(21)
        targetSdkVersion(34)
        versionCode = 1
        versionName = "1.0"
    }
}

kotlin {
    jvm()
    androidTarget()
    ios {
        binaries {
            framework {
                baseName = "shared"
            }
        }
    }
    wasm {
        browser {
            testTask {
                useKarma {
                    useChromeHeadless()
                }
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("aws.sdk.kotlin:cognitoidentityprovider:1.0.30")
                implementation("aws.sdk.kotlin:s3:1.0.30")
                implementation("io.ktor:ktor-client-core:2.3.7")
                implementation("io.ktor:ktor-server-auth:2.3.7")
                implementation("io.ktor:ktor-server-auth-jwt:2.3.7")
            }
        }
        val jvmMain by getting
        val androidMain by getting
        val iosMain by getting {
            dependsOn(commonMain)
        }
        val iosTest by getting {
            dependsOn(commonMain)
        }
        val wasmJsMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-js:2.3.7")
            }
        }
        val wasmJsTest by getting
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

tasks.register<JavaExec>("run") {
    group = "application"
    mainClass.set("com.velvetflow.cms.AppKt")
    classpath = sourceSets["jvmMain"].runtimeClasspath
    args = listOf() // Add any program arguments here
}