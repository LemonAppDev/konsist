pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "konsist-starter-kmp-gradle-kotlin-junit5"

include(":androidApp")
include(":shared")
include(":konsistTest")

