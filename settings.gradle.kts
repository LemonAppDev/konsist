rootProject.name = "konsist"

include(
    ":lib",
)

pluginManagement {
    includeBuild("build-logic")

    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}
