plugins {
    id("myapplication.android.library")
    id("myapplication.android.hilt")
}
android {}
dependencies {
    implementation(project(":core:data"))
}