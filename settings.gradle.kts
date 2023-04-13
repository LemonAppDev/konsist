rootProject.name = "mango"

include(
    ":app",
    ":konsist",
)

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}
