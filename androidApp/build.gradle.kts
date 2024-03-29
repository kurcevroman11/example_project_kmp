plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.vickikbt.devtyme.android"
    compileSdk = AndroidSdk.compileSdkVersion

    defaultConfig {
        applicationId = AndroidSdk.applicationId
        minSdk = AndroidSdk.minSdkVersion
        targetSdk = AndroidSdk.targetSdkVersion

        versionCode = AndroidSdk.versionCode
        versionName = AndroidSdk.versionName
        testInstrumentationRunner = AndroidSdk.testInstrumentationRunner

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    dependencies {
        implementation(project(":shared"))

        implementation(AndroidDependencies.androidCore)
        implementation(AndroidDependencies.appCompat)

        implementation(AndroidDependencies.material)

        implementation(AndroidDependencies.composeUi)
        implementation(AndroidDependencies.composeMaterial)
        implementation(AndroidDependencies.composeTooling)
        implementation(AndroidDependencies.composeConstraint)
        implementation(AndroidDependencies.composeLiveData)
        implementation(AndroidDependencies.composeActivity)

        implementation(AndroidDependencies.lifeCycleRuntime)

        // Koin-Dependency injection
        implementation(AndroidDependencies.koinAndroid)
        implementation(AndroidDependencies.koinCompose)

        // Accompanist Libs
        implementation(AndroidDependencies.accompanistNavigationAnimation)

        // Splash Screen API
        implementation(AndroidDependencies.splashScreen)

        // Coil-Image Loader
        implementation(AndroidDependencies.coil)

        // Compose Navigation-Navigation between various screens
        implementation(AndroidDependencies.navigation)

        coreLibraryDesugaring(AndroidDependencies.desugaring)

        // Lottie Compose
        implementation(AndroidDependencies.lottie)
    }
}
