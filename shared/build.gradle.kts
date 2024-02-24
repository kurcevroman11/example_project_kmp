plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization")
    id("app.cash.sqldelight")
}

kotlin {
    androidTarget()

    listOf(
        iosX64(),
        iosArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {

                implementation(KmmDependencies.kotlinxCoroutines)

                implementation(KmmDependencies.koinCore)

                implementation(KmmDependencies.kotlinxSerialization)

                implementation(KmmDependencies.ktorCore)
                implementation(KmmDependencies.ktorSerialization)
                implementation(KmmDependencies.ktorLogging)
                implementation(KmmDependencies.ktorClientAuth)

                implementation(KmmDependencies.sqlDelight)
                implementation(KmmDependencies.sqlDelightCoroutine)

                api(KmmDependencies.napier)

                implementation(KmmDependencies.kotlinxDateTime)

                implementation(KmmDependencies.multiplatformSettings)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(KmmDependencies.ktorAndroid)
                implementation(KmmDependencies.sqlDelightAndroid)
            }
        }

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)

            dependencies {
                implementation(KmmDependencies.ktoriOS)
                //implementation(KmmDependencies.sqlDelightIos)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                // implementation(KmmDependencies.mockk)
            }
        }
    }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }
}

android {
    namespace = "com.vickikbt.devtyme"
    compileSdk = AndroidSdk.compileSdkVersion
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = AndroidSdk.minSdkVersion
        targetSdk = AndroidSdk.targetSdkVersion
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

sqldelight {
    databases {
        create("AppDatabase") {
            packageName.set("com.vickikbt.devtyme.core.database")
        }
    }
}
