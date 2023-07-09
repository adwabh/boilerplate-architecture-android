plugins {
    id("myapplication.android.library")
    id("myapplication.android.hilt")
}
android {
    namespace = "com.example.myapplication.core.domain"
}
dependencies {
    implementation(project(":core:data"))
}