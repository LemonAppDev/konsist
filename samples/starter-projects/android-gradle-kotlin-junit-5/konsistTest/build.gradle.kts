import org.gradle.api.tasks.testing.logging.TestExceptionFormat

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.android.junit)
}

android {
    namespace = "com.sample"

    compileSdk = 37

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    kotlin {
        jvmToolchain(17)
    }
}

dependencies {
    // Add Appcompat dependency (to be able to access Android specific classes in Konsist tests)
    implementation(libs.androidx.appcompat)

    // Add JUnit dependency
    testImplementation(libs.junit.jupiter.api)
    testRuntimeOnly(libs.junit.jupiter.engine)

    // Add Konsist dependency
    testImplementation(libs.konsist)
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
