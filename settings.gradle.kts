rootProject.name = "konsist"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(
    ":lib",
)

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}
