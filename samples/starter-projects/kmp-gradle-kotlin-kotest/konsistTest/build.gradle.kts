import org.gradle.api.tasks.testing.logging.TestExceptionFormat

plugins {
    alias(libs.plugins.kotlinJvm)
}

kotlin {
    jvmToolchain(21)
}

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    testImplementation(libs.konsist)
    testImplementation(libs.kotest.runner.junit5)
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
