plugins {
    `kotlin-dsl`
}

group = "com.example.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "myapplication.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidDataBinding") {
            id = "myapplication.android.databinding"
            implementationClass = "AndroidDataBindingPlugin"
        }

        register("androidHilt") {
            id = "myapplication.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }

        register("androidApplicationCompose") {
            id = "myapplication.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
    }

}