plugins {
    id("myapplication.android.library")
    id("myapplication.android.hilt")
}

android {
    namespace = "com.example.myapplication.core.network"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.retrofit.core)
}