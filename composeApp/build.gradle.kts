import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.jetbrains.kotlin.multiplatform")
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
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