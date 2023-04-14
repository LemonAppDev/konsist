plugins {
    id("org.jetbrains.kotlin.jvm")
    id("local.spotless")
    id("local.test")
}

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(19)
}

tasks.withType<Test> {
    useJUnitPlatform()

    // Enable parallel test execution
    systemProperties = mapOf(
        "junit.jupiter.execution.parallel.enabled" to "true",
        "junit.jupiter.execution.parallel.mode.default " to "concurrent",
    )
}
