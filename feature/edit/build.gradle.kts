plugins {
    id("myapplication.android.library")
    id("myapplication.android.library.compose")
    id("myapplication.android.hilt")
}

android {
    namespace = "com.example.myapplication.feature.edit"
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.core.ktx)
}