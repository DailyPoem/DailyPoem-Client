buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:4.2.0")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Versions.Jetpack.Hilt}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Essential.Kotlin}")
    }
}




tasks.register("clean", Delete::class) {
    rootProject.buildDir.delete()
}