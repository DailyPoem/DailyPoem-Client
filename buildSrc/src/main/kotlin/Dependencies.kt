object Dependencies {

    val Essential = listOf(
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Essential.Coroutines}"
    )

    val Ktx = listOf(
        "androidx.core:core-ktx:${Versions.Ktx.Core}",
        "androidx.fragment:fragment-ktx:${Versions.Ktx.Fragment}",
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.Ktx.LifeCycle}",
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Ktx.ViewModel}",
        "androidx.navigation:navigation-fragment-ktx:${Versions.Ktx.Navigation}"
    )

    val Ui = listOf(
        "io.coil-kt:coil:${Versions.Ui.Coil}",
        "com.airbnb.android:lottie:${Versions.Ui.Lottie}",
        "androidx.appcompat:appcompat:${Versions.Ui.AppCompat}",
        "androidx.core:core-splashscreen:${Versions.Ui.Splash}",
        "com.google.android.flexbox:flexbox:${Versions.Ui.Flexbox}",
        "com.google.android.material:material:${Versions.Ui.Material}",
        "androidx.constraintlayout:constraintlayout:${Versions.Ui.ConstraintLayout}",
    )

    val Util = listOf(
        "land.sungbin:logeukes:${Versions.Util.Logeukes}",
    )
    val Jetpack = listOf(
        "com.google.dagger:hilt-android:${Versions.Jetpack.Hilt}"
    )

    val Compiler = listOf(
        "com.google.dagger:hilt-android-compiler:${Versions.Jetpack.Hilt}"
    )

    val Debug = listOf(
        "com.squareup.leakcanary:leakcanary-android:${Versions.Util.LeakCanary}"
    )
}