
buildscript {
    repositories {
        google()
        mavenCentral()

        // Android Build Server
        maven { url = uri("../nowinandroid-prebuilts/m2repository") }
    }

}

plugins {
    id("com.example.myapplication")
//    id("nowinandroid.android.application.compose")
//    id("nowinandroid.android.application.jacoco")
    id("com.example.myapplication.hilt")
//    id("jacoco")
//    id("nowinandroid.firebase-perf")
}