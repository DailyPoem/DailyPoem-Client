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
        "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.Ui.SwipeRefreshLayout}"
    )

    val Util = listOf(
        "land.sungbin:logeukes:${Versions.Util.Logeukes}",
    )

    val Kakao = listOf(
        "com.kakao.sdk:v2-user:2.4.2", // 카카오 로그인
        "com.kakao.sdk:v2-talk:2.4.2", // 친구, 메시지(카카오톡)
        "com.kakao.sdk:v2-story:2.4.2", // 카카오스토리
        "com.kakao.sdk:v2-link:2.4.2", // 메시지(카카오링크)
        "com.kakao.sdk:v2-navi:2.4.2", // 카카오내비

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

    val Retrofit = listOf(
        "com.squareup.retrofit2:retrofit:${Versions.Retrofit.Retrofit}",
        "com.squareup.retrofit2:converter-gson:${Versions.Retrofit.Retrofit}",

        // Logging Interceptor
        "com.squareup.okhttp3:logging-interceptor:${Versions.Retrofit.LoggingInterceptor}",

        // Gson
        "com.google.code.gson:gson:${Versions.Retrofit.Gson}"
    )
}