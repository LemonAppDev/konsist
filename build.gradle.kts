@Suppress("DSL_SCOPE_VIOLATION") // Because of IDE bug https://youtrack.jetbrains.com/issue/KTIJ-19370
plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.spotless)
}

dependencies {
    implementation(libs.kotlin.stdlib.jdk8)
    implementation(libs.kotlin.reflect)
    implementation(libs.kotlin.compiler)

    testImplementation(libs.junitJupiterEngine)
    testImplementation(libs.kluent)
    testImplementation(libs.mockk)
}
repositories {
    mavenCentral()
}

// Need to be here, so spotless works at root project level "gw spotlessCheck"
spotless {
    kotlin {
        ktlint()

        indentWithSpaces()
        endWithNewline()
    }

    // Don't add spotless as dependency for the Gradle's check task
    isEnforceCheck = false
}
