plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdkVersion = "31"
    buildToolsVersion = "30.0.3"
    buildFeatures {
        viewBinding = true
    }
    defaultConfig {
        applicationId = "com.example.myapplication"
        minSdkVersion(21)
        targetSdkVersion(31)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")
    }
    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            applicationIdSuffix = ".release"
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    val lifecycle_version = "2.4.0"
    val arch_version = "2.1.0"
    val retrofitVersion = "2.9.0"
    val gsonVersion = "2.9.0"
    val glideVersion = "4.12.0"
    val fragmentVersion = "1.1.0"
    val nav_version = "2.4.2"
    val kotlin_version: String by project
    val hilt_version: String by project

    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version")
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.0")
    implementation("com.google.android.material:material:1.4.0")


    testImplementation("junit:junit:4.+")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")

    //hilt
    implementation("com.google.dagger:hilt-android:$hilt_version")
    kapt("com.google.dagger:hilt-compiler:$hilt_version")
    kapt("androidx.hilt:hilt-compiler:1.0.0-alpha03")
    implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")

    //activity - essential for hilt injection in activities
    implementation("androidx.activity:activity-ktx:1.4.0")

    //fragment - essential for hilt injection in frags
    implementation("androidx.fragment:fragment-ktx:${fragmentVersion}")

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")

    // Saved state module for ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycle_version")

    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")

    // Navigation Feature module Support
    implementation("androidx.navigation:navigation-dynamic-features-fragment:$nav_version")

    // Testing Navigation
    androidTestImplementation("androidx.navigation:navigation-testing:$nav_version")

    // Annotation processor
    implementation("androidx.lifecycle:lifecycle-common-java8:$lifecycle_version")

    // optional - Test helpers for LiveData
    testImplementation("androidx.arch.core:core-testing:$arch_version")

    //okhttp
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")

    //retrofit
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")

    //gson
    implementation("com.google.code.gson:gson:$gsonVersion")

    //glide
    implementation("com.github.bumptech.glide:glide:$glideVersion")
    kapt("com.github.bumptech.glide:compiler:$glideVersion")
}