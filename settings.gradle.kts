pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()

        // ✅ Required for Compose Multiplatform
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

rootProject.name = "TargetIGNOU"
include(":app")
include(":shared")

// Map the conventional :app module to the actual directory name.
project(":app").projectDir = file("androidApp")
