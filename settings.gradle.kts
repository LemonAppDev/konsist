rootProject.name = "konsist"

include(
    ":lib",
    ":snippets",
)

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}
