import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}
val properties = Properties()
properties.load(project.rootProject.file("local.properties").inputStream())
android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.patrick.dailypoem"
        minSdk = Application.minSdk
        targetSdk = Application.targetSdk
        versionCode = Application.versionCode
        versionName = Application.versionName
        buildConfigField("String", "KEY", properties["TOKEN"].toString())
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

    testImplementation("junit:junit:4.12")
    val dependencies = listOf(
        Dependencies.Ui,
        Dependencies.Ktx,
        Dependencies.Util,
        Dependencies.Essential,
        Dependencies.Jetpack,
        Dependencies.Retrofit,
        Dependencies.Kakao
    ).flatten()

    dependencies.forEach(::implementation)
    Dependencies.Debug.forEach(::debugImplementation)

    Dependencies.Compiler.forEach(::kapt)
}
