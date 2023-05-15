plugins {
    id("myapplication.android.library")
    id("myapplication.android.library.compose")
    id("myapplication.android.hilt")
}

android {
    namespace = "com.example.myapplication.feature.home"
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.core.ktx)

    implementation(project(":core:ui"))
    implementation(project(":core:data"))

    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.foundation.layout)
    api(libs.androidx.compose.material3)
    debugApi(libs.androidx.compose.ui.tooling)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.androidx.compose.ui.util)
    api(libs.androidx.compose.runtime)
    api(libs.androidx.compose.runtime.livedata)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
}