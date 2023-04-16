
plugins {
    id("myapplication.android.application")
    id("myapplication.android.application.compose")
//    id("nowinandroid.android.application.jacoco")
//    id("myapplication.android.library")
    id("myapplication.android.hilt")
//    id("jacoco")
//    id("nowinandroid.firebase-perf")
}

android {
    defaultConfig {
        applicationId = "com.example.myapplication"
        versionCode = 4
        versionName = "0.0.4" // X.Y.Z; X = Major, Y = minor, Z = Patch level

        // Custom test runner to set up Hilt dependency graph
        testInstrumentationRunner = "com.example.myapplication.core.testing.TestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    namespace = "com.example.myapplication"
}

dependencies {
//    api(libs.androidx.compose.material.iconsExtended)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.foundation.layout)
//    api(libs.androidx.compose.material.iconsExtended)
    api(libs.androidx.compose.material3)
    debugApi(libs.androidx.compose.ui.tooling)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.androidx.compose.ui.util)
    api(libs.androidx.compose.runtime)
    api(libs.androidx.compose.runtime.livedata)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.constraint.layout)
    implementation(libs.gson.parser)
    implementation(libs.gson.converter)
    implementation(libs.appcompat.activity)
    implementation(libs.appcompat.resources)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
}

buildscript {
    repositories {
        google()
        mavenCentral()

        // Android Build Server
        maven { url = uri("../nowinandroid-prebuilts/m2repository") }
    }

}

