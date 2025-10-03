rootProject.name = "konsist"

include(
    ":lib",
)

pluginManagement {
    includeBuild("buildLogic")

    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}
