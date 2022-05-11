object Dependency {

    object Kotlin {
        const val SDK = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.5.21"
    }

    object Compose {
        const val UI = "androidx.compose.ui:ui:${Versions.COMPOSE}"
        const val UI_TOOLING = "androidx.compose.foundation:foundation:${Versions.COMPOSE}"
        const val MATERIAL = "androidx.compose.material:material:${Versions.COMPOSE}"
        const val MATERIAL_ICON_CORE ="androidx.compose.material:material-icons-core:${Versions.COMPOSE}"
        const val MATERIAL_ICON_EXTENDED ="androidx.compose.material:material-icons-extended:${Versions.COMPOSE}"
        const val JUNIT = "androidx.compose.ui:ui-test-junit4:${Versions.COMPOSE}"

    }

    object AndroidX {
        const val MATERIAL = "com.google.android.material:material:1.5.0"
        const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:2.1.3"
        const val APP_COMPAT = "androidx.appcompat:appcompat:1.3.1"
    }

    object KTX {
        const val CORE = "androidx.core:core-ktx:1.7.0"
    }

    object Test {
        const val JUNIT = "junit:junit:4.13.2"
        const val ANDROID_JUNIT_RUNNER = "AndroidJUnitRunner"
    }

    object AndroidTest {
        const val TEST_RUNNER = "androidx.test.ext:junit:1.1.3"
        const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:3.4.0"
    }
}