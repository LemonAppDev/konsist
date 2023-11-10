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

rootProject.name = "konsist-starter-kmp-gradle-kotlin-kotest"

include(":androidApp")
include(":shared")
include(":konsistTest")

