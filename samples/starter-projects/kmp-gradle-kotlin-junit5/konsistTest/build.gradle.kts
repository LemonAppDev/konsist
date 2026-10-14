import org.gradle.api.tasks.testing.logging.TestExceptionFormat

plugins {
    alias(libs.plugins.kotlinJvm)
}

kotlin {
    jvmToolchain(21)
}

dependencies {
    // Add Konsist dependency
    testImplementation(libs.konsist)

    // Add JUnit 5 dependency
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.platform.launcher)
}

tasks.withType<Test> {
    // Configure JUnit 5 tests
    useJUnitPlatform()
}

// Optional block to show Konsist assertion messages (invalid declarations, file links, reasons) in the command line.
// Gradle uses the SHORT exception format by default, which prints only the exception class name, so the message is
// visible only in the HTML test report. Stack traces are hidden, because they point to Konsist internals.
tasks.withType<Test>().configureEach {
    testLogging {
        exceptionFormat = TestExceptionFormat.FULL
        showStackTraces = false
    }
}
