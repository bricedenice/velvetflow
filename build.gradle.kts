import com.android.kotlin.multiplatform.ide.models.serialization.androidTargetKey
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    // Kotlin
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.kotlinCocoapods) apply false
    
    // Android
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    
    // Compose
    alias(libs.plugins.jetbrainsCompose) apply false
    
    // Serialization
    alias(libs.plugins.kotlinSerialization) apply false
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
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.ui)
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")
                implementation("io.ktor:ktor-server-core:2.3.7")
                implementation("io.ktor:ktor-server-auth:2.3.7")
                implementation("io.ktor:ktor-server-auth-jwt:2.3.7")
                implementation("aws.sdk.kotlin:cognito-idp:1.0.30")
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("androidx.activity:activity-compose:1.8.2")
                implementation("androidx.appcompat:appcompat:1.6.1")
            }
        }
        val iosMain by getting {
            dependsOn(commonMain)
        }
        val iosTest by getting {
            dependsOn(commonMain)
        }
        val wasmJsMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-js:2.3.7")
                implementation(compose.html.core)
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