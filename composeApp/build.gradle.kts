import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.kotlinCocoapods)
}

kotlin {
    jvm {
        withJava()
    }
    this.androidTarget()
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    wasmJs()

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
        val iosMain by creating {
            dependsOn(commonMain)
        }
        val iosTest by creating {
            dependsOn(commonMain)
        }
        val wasmJsMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-js:2.3.7")
            }
        }
        val wasmJsTest by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
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

// Android configuration
android {
    namespace = "your.package.name"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}