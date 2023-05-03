pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
include(":app")
include(":core:common")
include(":core:domain")
include(":core:data")
include(":core:network")
include(":core:designsystem")
include(":core:ui")
include(":core:database")
rootProject.name = "My Application"