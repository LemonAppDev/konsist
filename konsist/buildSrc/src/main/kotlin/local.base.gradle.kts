plugins {
    id("org.jetbrains.kotlin.jvm")
    id("com.diffplug.spotless")
    id("org.gradle.jvm-test-suite")
    id("com.adarshr.test-logger")
    id("io.gitlab.arturbosch.detekt")
}

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(19)
}

spotless {
    kotlin {
        ktlint()

        indentWithSpaces()
        endWithNewline()
    }

    // Don't add spotless as dependency for the Gradle's check task to facilitate separated codebase checks
    isEnforceCheck = false
}

tasks.withType<Test> {
    useJUnitPlatform()

    // Enable parallel test execution
    systemProperties = mapOf(
        "junit.jupiter.execution.parallel.enabled" to "true",
        "junit.jupiter.execution.parallel.mode.default " to "concurrent",
    )
}
