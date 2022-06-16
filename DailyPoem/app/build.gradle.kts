plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.patrick.dailypoem"
        minSdk = Application.minSdk
        targetSdk = Application.targetSdk
        versionCode = Application.versionCode
        versionName = Application.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = Application.sourceCompat
        targetCompatibility = Application.targetCompat
    }
    kotlinOptions {
        jvmTarget = Application.jvmTarget
    }
    buildFeatures {
        dataBinding = true
    }
}

dependencies {

    val dependencies = listOf(
        Dependencies.Ui,
        Dependencies.Ktx,
        Dependencies.Util,
        Dependencies.Essential,
        Dependencies.Jetpack,
    ).flatten()


    dependencies.forEach(::implementation)
    Dependencies.Debug.forEach(::debugImplementation)

    Dependencies.Compiler.forEach(::kapt)
}

