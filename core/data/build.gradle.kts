plugins {
    id("myapplication.android.library")
    id("myapplication.android.hilt")
}
android {
    namespace = "com.example.myapplication.core.data"
}
dependencies {
    implementation(project(":core:network"))
    implementation(project(":core:database"))
}