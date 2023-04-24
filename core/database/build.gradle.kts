plugins {
    id("myapplication.android.library")
    id("myapplication.android.hilt")
    id("myapplication.android.room")
}
android {
    namespace = "com.example.myapplication.core.database"
}
dependencies {
//    implementation(project(":core:model"))
    implementation(libs.kotlinx.coroutines.android)
}
