plugins {
    id("com.lemonappdev.konsist.convention.detekt")
    id("com.lemonappdev.konsist.convention.kotlin")
    id("com.lemonappdev.konsist.convention.publishaggregation")
    id("com.lemonappdev.konsist.convention.spotless")
}

dependencies {
    nmcpAggregation(project(":lib"))
}

// Test projects are standalone Gradle builds, so ktlint runs on them from the root project
spotless {
    kotlin {
        target("misc/projects/test-projects/**/*.kt")
    }

    kotlinGradle {
        target("misc/projects/test-projects/**/*.gradle.kts")
    }
}
